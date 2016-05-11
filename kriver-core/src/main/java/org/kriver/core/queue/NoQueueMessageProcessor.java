package org.kriver.core.queue;

import org.apache.log4j.Logger;
import org.kriver.core.CoreErrors;
import org.kriver.core.common.ErrorsUtil;
import org.kriver.core.netty.IKMessageBase;
import org.kriver.core.netty.ISenderMessage;

/**
 * 不带队列的消息处理器,即当接收到消息后不放入队列中直接处理
 * 
 * @see {@link QueueMessageProcessor}
 * @version 2009-7-8 上午10:12:18
 */
public class NoQueueMessageProcessor implements IMessageProcessor {

	/** 日志 */
	private static final Logger logger = Logger.getLogger(NoQueueMessageProcessor.class);

	/** 消息处理器 */
	private final IMessageHandler<IKMessageBase> handler;

	/** 停止处理标识 */
	@SuppressWarnings("unused")
	private volatile boolean stop = false;

	@SuppressWarnings("unchecked")
	public NoQueueMessageProcessor(IMessageHandler messageHandler) {
		handler = messageHandler;
	}

	/**
	 * 将待处理消息放入队列 ： 这里暂时直接处理
	 */
	public void put(IKMessageBase msg) {
		process(msg);
	}

	/**
	 * 处理具体的消息，每个消息有自己的参数和来源,如果在处理消息的过程中发生异常,则马上将此消息的发送者断掉
	 * 
	 * @param msg
	 */
	@SuppressWarnings("unchecked")
	public void process(IKMessageBase msg) {
		if (msg == null) {
			logger.warn("[#CORE.NoQueueMessageProcessor.process] [" + CoreErrors.MSG_PRO_ERR_NULL_MSG + "]");
			return;
		}
		final long begin = System.nanoTime();
		
		try {
			this.handler.execute(msg);
		} catch (Exception e) {
			logger.error(ErrorsUtil.error("Error", "#.NoQueueMessageProcessor.process", "param"), e);			
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
			} catch (Exception e1) {
				logger.error(ErrorsUtil.error(CoreErrors.MSG_PRO_ERR_DIS_SENDER_FAIL,
						"#CORE.NoQueueMessageProcessor.process", null), e1);
			}
		} finally {
			if (logger.isInfoEnabled()) {
				// 特例，统计时间跨度
				long time = (System.nanoTime() - begin)/1000/1000;
				if (time > 1) {
					int type = msg.getType();
					logger.debug("#CORE.MSG.PROCESS.STATICS Message Name:" + msg.getTypeName() + " Type:" + type
							+ " Time:" + time + "ms");
				}
			}
		}
	}

	/**
	 * 开始处理
	 */
	public synchronized void start() {
		stop = false;
	}

	/**
	 * 停止处理
	 */
	public synchronized void stop() {
		stop = true;
	}

	@Override
	public boolean isFull() {
		return false;
	}

}
