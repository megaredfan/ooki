package org.kriver.core.common;

import java.util.UUID;

/**
 * 产生各种key的工具类
 * 
 * @author <a href="mailto:dongyong.wang@opi-corp.com">wang dong yong<a>
 * 
 */
public class KeyUtil {
	/** Key的长度 */
	public static final int KEY_LEN = 32;
	
	/**
	 * 产生一个UUID的Key
	 * 
	 * @return
	 */
	public static String UUIDKey() {
		return toShortKey(genUUIDKey());
	}

	/**
	 * 生成UUID字符串
	 * 
	 * @return
	 */
	static String genUUIDKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获得与时间相关的uuid， 格式为高32位：id， 低32位：YYMMDDhhmm
	 * 
	 * @param curId
	 * @return
	 */
	public static long genTimeUUIDKey(int curId) {
		int _curTimeVersion = TimeUtils.getYYMMDDhhmmOfTime();
		return (((long)curId << 32) |  _curTimeVersion);
	}
	
	/**
	 * 去掉UUID的字符串中的"-"符号,以取得较短的key值
	 * 
	 * @param key
	 * @return
	 */
	static String toShortKey(String key) {
		return key.substring(0, 8) + key.substring(9, 13) + key.substring(14, 18) + key.subSequence(19, 23)
				+ key.substring(24);
	}
	
	/**
	 * 取得指定长度UUID
	 * 
	 * 从0开始截取
	 * 
	 * @param key
	 * @return
	 */
	public static String getLenKey(int len) {
		if (len >= KEY_LEN) {
			return null;
		}
		String uid = UUIDKey();
		return uid.substring(0, len);
	}
}
