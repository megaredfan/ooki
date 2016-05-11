package org.kriver.core.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * 消息的发送者接口
 * 
 */
public interface Sender {
	
	/** Sender 状态 : 连接刚刚创建 */
	public static final int STATE_CONNECTED = 0;
	
	/** Sender 状态 : 连接已断开 */
	public static final int STATE_DISCONNECTED = 1;

	/**
	 * 断开连接
	 */
	public void disconnect();

	/**
	 * 设置消息发送者的状态
	 * 
	 * @param state
	 */
	public void setState(int state);

	/**
	 * 取得消息发送者的状态
	 * 
	 * @return
	 */
	public int getState();

	/**
	 * 设置与发送者绑定的session
	 * 
	 * @param session
	 */
	public void setSession(ChannelHandlerContext session);
	
	/**
	 * 发生异常时是否可以关闭连接
	 * @return true,可以关闭连接;false,不主动关闭连接
	 */
	public boolean closeOnException();
	
	
}
