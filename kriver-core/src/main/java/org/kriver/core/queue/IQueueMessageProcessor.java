package org.kriver.core.queue;

import java.util.List;

import org.kriver.core.netty.IKMessageBase;

/**
 * 队列类型的消息处理器
 * 
 */
public interface IQueueMessageProcessor extends IMessageProcessor {
	/**
	 * 获取未处理的消息队列
	 * @return
	 */
	public List<IKMessageBase> getLeftQueue();
	
	/**
	 * 处理消息
	 * @param msg
	 */
	public void process(IKMessageBase msg);
	
	/**
	 * 重置消息队列
	 */
	public void resetLeftQueue();

}
