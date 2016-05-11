package org.kriver.core.queue;

import org.kriver.core.netty.IKMessageBase;

/**
 * 消息逻辑处理器
 * 
 * @author <a href="mailto:dongyong.wang@opi-corp.com">wang dong yong<a>
 * 
 */
public interface IMessageHandler<T extends IKMessageBase> {
	/**
	 * 处理消息
	 * 
	 * @param message
	 *            需要被处理的消息
	 */
	public void execute(T message);

	/**
	 * 取得此处理器可以处理的消息类型
	 * 
	 * @return
	 */
	public short[] getTypes();
}
