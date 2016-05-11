package org.kriver.core.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.kriver.core.CoreErrors;
import org.kriver.core.common.ErrorsUtil;
import org.kriver.core.common.ExecutorUtil;
import org.kriver.core.netty.IKMessageBase;
import org.kriver.core.netty.ISenderMessage;

/**
 * 基于队列的消息处理器,使用{@link ExecutorService}处理队列中消息
 * 
 * @author <a href="mailto:dongyong.wang@opi-corp.com">wang dong yong<a>
 * @author <a href="mailto:yong.fang@renren-inc.com">fang yong</a>
 * @see {@link NoQueueMessageProcessor}
 */
public class QueueMessageExecutorProcessor implements IQueueMessageProcessor {
	private static final Logger log = org.apache.log4j.Logger.getLogger(QueueMessageExecutorProcessor.class);

	/** 消息队列 * */
	private final BlockingQueue<IKMessageBase> queue;

	/** 消息处理线程停止时剩余的还未处理的消息 **/
	private volatile List<IKMessageBase> leftQueue;

	/** 消息处理器 * */
	private final IMessageHandler<IKMessageBase> handler;

	/** 消息处理线程池 */
	private volatile ExecutorService executorService;

	/** 线程池的线程个数 */
	private int excecutorCoreSize;

	/** 是否停止 */
	private volatile boolean stop = false;

	/** 处理的消息总数 */
	private long statisticsMessageCount = 0;

	private final boolean processLeft;

	@SuppressWarnings("unchecked")
	public QueueMessageExecutorProcessor(IMessageHandler messageHandler) {
		this(messageHandler, false, 1);
	}

	@SuppressWarnings("unchecked")
	public QueueMessageExecutorProcessor(IMessageHandler messageHandler, boolean processLeft, int executorCoreSize) {
		queue = new LinkedBlockingQueue<IKMessageBase>();
		handler = messageHandler;
		this.processLeft = processLeft;
		this.excecutorCoreSize = executorCoreSize;
	}

	@Override
	public void put(IKMessageBase msg) {
		try {
			queue.put(msg);
			log.debug("put queue size:" + queue.size());
		} catch (InterruptedException e) {
			log.error(CoreErrors.THRAD_ERR_INTERRUPTED, e);
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
			log.warn("[#CORE.QueueMessageExecutorProcessor.process] [" + CoreErrors.MSG_PRO_ERR_NULL_MSG + "]");
			return;
		}
		final long begin = System.nanoTime();
		this.statisticsMessageCount++;
	
		try {
			this.handler.execute(msg);
		} catch (Throwable e) {
			log.error(ErrorsUtil.error("Error", "#.QueueMessageExecutorProcessor.process", "param"), e);
			try {
				// TODO 此处的逻辑应该由一个接口代为实现
				if (msg instanceof ISenderMessage) {
					// 如果在处理消息的过程中出现了错误,将其断掉
					final ISenderMessage imsg = (ISenderMessage) msg;
					if (imsg.getSender() != null && imsg.getSender().closeOnException()) {
							log.error(ErrorsUtil.error(CoreErrors.MSG_PRO_ERR_EXP, "Disconnect sender", msg
									.getTypeName()
									+ "@" + imsg.getSender()), e);
						imsg.getSender().disconnect();
					}
				}
			} catch (Throwable e1) {
				log.error(ErrorsUtil.error(CoreErrors.MSG_PRO_ERR_DIS_SENDER_FAIL,
						"#CORE.QueueMessageProcessor.process", null), e1);
			}
		} finally {
			// 特例，统计时间跨度
			long time = (System.nanoTime() - begin) / (1000 * 1000);
			if (time > 1) {
				int type = msg.getType();
				log.info("#CORE.MSG.PROCESS.STATICS Message Name:" + msg.getTypeName() + " Type:" + type
						+ " Time:" + time + "ms" + " Total:" + this.statisticsMessageCount);
			}
		}
	}

	public IMessageHandler<IKMessageBase> getHandler() {
		return handler;
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
	 * 开始消息处理
	 */
	public synchronized void start() {
		if (this.executorService != null) {
			throw new IllegalStateException("The executorSerive has not been stopped.");
		}
		stop = false;
		this.executorService = Executors.newFixedThreadPool(this.excecutorCoreSize);
		for (int i = 0; i < this.excecutorCoreSize; i++) {
			this.executorService.execute(new Worker());
		}
		log.info("Message processor executorService started [" + this.executorService + " with "
				+ this.excecutorCoreSize + " threads ]");
	}

	/**
	 * 停止消息处理
	 */
	public synchronized void stop() {
		log.info("Message processor executor "+this+" stopping ...");
		this.stop = true;
		if (this.executorService != null) {
			ExecutorUtil.shutdownAndAwaitTermination(this.executorService,50,TimeUnit.MILLISECONDS);
			this.executorService = null;
		}
		log.info("Message processor executor "+this+" stopped");
		if (this.processLeft) {
			//将未处理的消息放入到leftQueue中,以备后续处理
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
		this.queue.clear();
	}

	/**
	 * 达到5000上限时认为满了
	 */
	@Override
	public boolean isFull() {
		return this.queue.size() > 5000;
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

	private final class Worker implements Runnable {
		@Override
		public void run() {
			while (!stop) {
				try {
					process(queue.take());
					log.debug("run queue size:" + queue.size());
				} catch (InterruptedException e) {
					log.warn("[#CORE.QueueMessageExecutorProcessor.run] [Stop process]");
					Thread.currentThread().interrupt();
					break;
				} catch (Exception e) {
					log.error(CoreErrors.MSG_PRO_ERR_EXP, e);
				}
			}
		}
	}
}
