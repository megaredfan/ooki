package org.kriver.core.netty;


/**
 * 
 */
public interface IKMessageBase {
	/**
	 * 取得该消息的类型
	 * 
	 * @return
	 */
	public int getType();

	/**
	 * 取得该消息的名称
	 * 
	 * @return
	 */
	public String getTypeName();
}
