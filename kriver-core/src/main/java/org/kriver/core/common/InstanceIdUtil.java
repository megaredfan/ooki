package org.kriver.core.common;

public class InstanceIdUtil {

	/**
	 * 对于没有并发的情况，要求精度在秒级别的应用类型，随机生成一个int类型的id
	 * @return
	 */
	public static int randomIntId(){
		long id = System.currentTimeMillis();
		return (int)(id/1000);
	}
	/**
	 * 对于没有并发需求，返回纳秒级的随机id，可以满足大部分情况
	 * @return
	 */
	public static long randomLongId(){
		return System.nanoTime();
	}
}
