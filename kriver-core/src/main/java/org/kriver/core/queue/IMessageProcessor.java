package org.kriver.core.queue;

import org.kriver.core.netty.IKMessageBase;

/**
 * 消息处理器接口
 */
public interface IMessageProcessor {
	/**
	 * 启动消息处理器
	 */
	public void start();

	/**
	 * 停止消息处理器
	 */
	public void stop();

	/**
	 * 向消息队列投递消息
	 * 
	 * @param msg
	 */
	public void put(IKMessageBase msg);

	/**
	 * 判断队列是否已经达到上限了
	 * @return
	 */
	public boolean isFull();
}