package org.kriver.core.uuid;

import java.io.Serializable;

/**
 * UUID接口
 *
 * @param <T>
 */
public interface IUUID<T extends Serializable> {

	/**
	 * 取得一个递增的UUID
	 * 
	 * @return
	 * @exception IllegalStateException
	 *                如果当前取得的UUID大于{@link #maxUUID},表示发生了溢出,会抛出此异常
	 */
	public T getNextUUID();
	
	/**
	 * 取得当前的UUID
	 * @return
	 */
	public T getCurUUID();
}
