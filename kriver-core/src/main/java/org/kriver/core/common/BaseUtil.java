package org.kriver.core.common;

/**
 * 一些基本变量类型与byte[]的转化
 * 
 * @author bear
 * 
 */
public class BaseUtil {

	public static byte[] longToByteArray(long num) {
		byte[] result = new byte[8];
		result[0] = (byte) (num >>> 56);// 取最高8位放到0下标
		result[1] = (byte) (num >>> 48);// 取最高8位放到0下标
		result[2] = (byte) (num >>> 40);// 取最高8位放到0下标
		result[3] = (byte) (num >>> 32);// 取最高8位放到0下标
		result[4] = (byte) (num >>> 24);// 取最高8位放到0下标
		result[5] = (byte) (num >>> 16);// 取次高8为放到1下标
		result[6] = (byte) (num >>> 8); // 取次低8位放到2下标
		result[7] = (byte) (num); // 取最低8位放到3下标
		return result;
	}

	public static long byteArrayToLong(byte[] byteArray) {
		byte[] a = new byte[8];
		int i = a.length - 1, j = byteArray.length - 1;
		for (; i >= 0; i--, j--) {// 从b的尾部(即int值的低位)开始copy数据
			if (j >= 0)
				a[i] = byteArray[j];
			else
				a[i] = 0;// 如果b.length不足4,则将高位补0
		}
		// 注意此处和byte数组转换成int的区别在于，下面的转换中要将先将数组中的元素转换成long型再做移位操作，
		// 若直接做位移操作将得不到正确结果，因为Java默认操作数字时，若不加声明会将数字作为int型来对待，此处必须注意。
		// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		long v0 = (long) (a[0] & 0xff) << 56;
		long v1 = (long) (a[1] & 0xff) << 48;
		long v2 = (long) (a[2] & 0xff) << 40;
		long v3 = (long) (a[3] & 0xff) << 32;
		long v4 = (long) (a[4] & 0xff) << 24;
		long v5 = (long) (a[5] & 0xff) << 16;
		long v6 = (long) (a[6] & 0xff) << 8;
		long v7 = (long) (a[7] & 0xff);
		return v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7;
	}

	public static byte[] intToByteArray(int num) {
		byte[] result = new byte[4];
		result[0] = (byte) (num >>> 24);// 取最高8位放到0下标
		result[1] = (byte) (num >>> 16);// 取次高8为放到1下标
		result[2] = (byte) (num >>> 8); // 取次低8位放到2下标
		result[3] = (byte) (num); // 取最低8位放到3下标
		return result;
	}

	public static int byteArrayToInt(byte[] b) {
		byte[] a = new byte[4];
		int i = a.length - 1, j = b.length - 1;
		for (; i >= 0; i--, j--) {// 从b的尾部(即int值的低位)开始copy数据
			if (j >= 0)
				a[i] = b[j];
			else
				a[i] = 0;// 如果b.length不足4,则将高位补0
		}
		int v0 = (a[0] & 0xff) << 24;// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		int v1 = (a[1] & 0xff) << 16;
		int v2 = (a[2] & 0xff) << 8;
		int v3 = (a[3] & 0xff);
		return v0 + v1 + v2 + v3;
	}

	public static void main(String[] args) {
		long a = 887733L;
		byte[] as = BaseUtil.longToByteArray(a);
		System.out.println(BaseUtil.byteArrayToLong(as));
		int b = 2455555;
		byte[] bs = BaseUtil.intToByteArray(b);
		System.out.println(BaseUtil.byteArrayToInt(bs));
	}
}
