package org.kriver.core.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.kriver.core.CoreErrors;
import org.kriver.core.common.ErrorsUtil;
import org.kriver.core.netty.IKMessageBase;
import org.kriver.core.netty.ISenderMessage;

/**
 * 基于队列的消息处理器,使用单线程依次用于处理队列中消息
 * 
 * @see {@link NoQueueMessageProcessor}
 */
public class QueueMessageProcessor implements Runnable, IQueueMessageProcessor {
	private static final Logger logger = Logger.getLogger(QueueMessageProcessor.class);

	/** 消息队列 * */
	private final BlockingQueue<IKMessageBase> queue;

	/** 消息处理线程停止时剩余的还未处理的消息 **/
	private volatile List<IKMessageBase> leftQueue;

	/** 消息处理器 * */
	private final IMessageHandler<IKMessageBase> handler;

	/** 消息处理线程 */
	private volatile Thread messageProcessorThread;

	/** 运行的线程id * */
	private long threadId;

	/** 是否停止 */
	private volatile boolean stop = false;

	/** 处理的消息总数 */
	private long statisticsMessageCount = 0;

	/** 等待消息处理线程退出 */
	private volatile CountDownLatch stopLatch;

	private final boolean processLeft;
	
	private final String name;

	/**  消息接收数上限， 达到上限后接受消息将挂起， 等待恢复 */
	private final int msgLimit;
	
	/** 默认消息接收数上限 */
	private static final int DEFAULT_MSG_LIMIT = 5000;
	
	@SuppressWarnings("unchecked")
	public QueueMessageProcessor(IMessageHandler messageHandler) {
		this(messageHandler, false);
	}

	@SuppressWarnings("unchecked")
	public QueueMessageProcessor(IMessageHandler messageHandler, int msgLimit) {
		this(messageHandler, false, msgLimit);
	}
	
	@SuppressWarnings("unchecked")
	public QueueMessageProcessor(IMessageHandler messageHandler, boolean processLeft) {
		this(messageHandler, processLeft, "NULL", DEFAULT_MSG_LIMIT);
	}

	@SuppressWarnings("unchecked")
	public QueueMessageProcessor(IMessageHandler messageHandler, boolean processLeft, int msgLimit) {
		this(messageHandler, processLeft, "NULL", msgLimit);
	}
	
	@SuppressWarnings("unchecked")
	public QueueMessageProcessor(IMessageHandler messageHandler, boolean processLeft,
			String name, int msgLimit) {
		queue = new LinkedBlockingQueue<IKMessageBase>();
		handler = messageHandler;
		this.processLeft = processLeft;
		this.name = name;
		this.msgLimit = msgLimit;
	}

	@Override
	public void put(IKMessageBase msg) {
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			logger.error(CoreErrors.THRAD_ERR_INTERRUPTED, e);
		}
	}

	/**
	 * 主处理函数，从消息队列中阻塞取出消息，然后处理
	 */
	public void run() {
		threadId = Thread.currentThread().getId();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		this.stopLatch = countDownLatch;
		try {
			while (!stop) {
				try {
					process(queue.take());
				} catch (InterruptedException e) {
					logger.error("[#CORE.QueueMessageProcessor.run] [Stop process] exception:", e);
					break;
				} catch (Throwable e) {
					logger.error(CoreErrors.MSG_PRO_ERR_EXP, e);
				}
			}
		} finally {
			logger.info(Thread.currentThread() + " Stop");
			countDownLatch.countDown();
		}
	}

	/**
	 * 处理具体的消息，每个消息有自己的参数和来源,如果在处理消息的过程中发生异常,则马上将此消息的发送者断掉
	 * 
	 * @param msg
	 */
	@SuppressWarnings("unchecked")
	public void process(IKMessageBase msg) {
		if (msg == null) {
			logger.warn("[#CORE.QueueMessageProcessor.process] [" + CoreErrors.MSG_PRO_ERR_NULL_MSG + "]");
			return;
		}
		final long begin = System.nanoTime();
		this.statisticsMessageCount++;
		
		try {
			this.handler.execute(msg);

		} catch (Throwable e) {
			logger.error(ErrorsUtil.error("Error", "#.QueueMessageProcessor.process", "param"), e);
			try {
				// TODO 此处的逻辑应该由一个接口代为实现
				if (msg instanceof ISenderMessage) {
					// 如果在处理消息的过程中出现了错误,将其断掉
					final ISenderMessage imsg = (ISenderMessage) msg;
					if (imsg.getSender() != null && imsg.getSender().closeOnException()) {
						logger.error(ErrorsUtil.error(CoreErrors.MSG_PRO_ERR_EXP, "Disconnect sender", msg
									.getTypeName()
									+ "@" + imsg.getSender()), e);
						imsg.getSender().disconnect();
					}
				}
			} catch (Throwable secExcept) {
				logger.error(ErrorsUtil.error(CoreErrors.MSG_PRO_ERR_DIS_SENDER_FAIL,
						"#CORE.QueueMessageProcessor.process", null), secExcept);
			} 
		} finally {
			long nanotime = System.nanoTime() - begin;
			// 特例，统计时间跨度
			long time = TimeUnit.NANOSECONDS.toMicros(nanotime);
			if (time > 1000) {
				int type = msg.getType();
				logger.info("#CORE.MSG.PROCESS.STATICS Message Name:" + msg.getTypeName() + " Type:" + type + " Time:"
						+ time + "us" + " Total:" + this.statisticsMessageCount);
			}
		}
	}

	public IMessageHandler<IKMessageBase> getHandler() {
		return handler;
	}

	public long getThreadId() {
		return threadId;
	}

	/**
	 * 取得未处理消息队列的长度
	 * 
	 * @return
	 */
	public int getQueueLength() {
		return queue.size();
	}

	/**
	 * 停止消息处理
	 */
	public synchronized void stop() {
		logger.info("Message processor thread " + this.messageProcessorThread + " stopping ...");
		this.stop = true;
		if (this.messageProcessorThread != null) {
			this.messageProcessorThread.interrupt();
		}
		logger.info("Message processor thread " + this.messageProcessorThread + " interrupted");
		this.messageProcessorThread = null;
		// 需要处理完未处理的消息
		// 等待消息处理线程退出
		try {
			final CountDownLatch _stopLatch = this.stopLatch;
			if (_stopLatch != null) {
				_stopLatch.await();
			}
			// 将未处理的消息放入到leftQueue中,以备后续处理
			if (this.processLeft) {
				this.leftQueue = new LinkedList<IKMessageBase>();
				while (true) {
					IKMessageBase _msg = this.queue.poll();
					if (_msg != null) {
						this.leftQueue.add(_msg);
					} else {
						break;
					}
				}
			}
		} catch (InterruptedException e) {
		}
		this.queue.clear();
	}

	/**
	 * 开始消息处理
	 */
	public synchronized void start() {
		stop = false;
		if (this.messageProcessorThread != null) {
			throw new IllegalStateException("The thread has already run" + this.messageProcessorThread);
		}
		this.messageProcessorThread = new Thread(this, "MessageProcessor_Thread_" + this.name);
		this.messageProcessorThread.start();
		logger.info("Message processor thread started [" + this.messageProcessorThread + "]");
	}

	/**
	 * 达到5000上限时认为满了
	 */
	@Override
	public boolean isFull() {
		return this.queue.size() > msgLimit;
	}

	/**
	 * 取得消息处理器停止后的遗留的消息
	 * 
	 * @return the leftQueue
	 */
	public List<IKMessageBase> getLeftQueue() {
		return leftQueue;
	}

	/**
	 * 重置
	 */
	public void resetLeftQueue() {
		this.leftQueue = null;
	}

}
