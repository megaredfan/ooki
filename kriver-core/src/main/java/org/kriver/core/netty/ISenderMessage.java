package org.kriver.core.netty;


/**
 * 带有发送者的消息接口
 * 
 * @param <T>
 *            发送者的类型
 */
public interface ISenderMessage<T extends Sender> extends IKMessageBase {
	/**
	 * 取得此消息的发送者
	 * 
	 * @return
	 */
	public T getSender();

	/**
	 * 设置该消息的发送者
	 * 
	 * @param sender
	 */
	public void setSender(T sender);
}
