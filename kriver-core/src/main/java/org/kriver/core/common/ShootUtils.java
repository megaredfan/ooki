package org.kriver.core.common;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Shoot机制的工具函数
 * 
 * 生成随机汉字
 * 
 * 生成随机颜色
 * 
 * 生成随机Shoot图片，包含随机的汉字和颜色
 * 
 * 建议使用过程 1. 预处理阶段，生成一组图片，每个图片一个文件+文字，每次系统启动阶段生成 jpegdata byte[] + chinese
 * string
 * 
 * 2. 每次使用的时候，传递给对方三个选项和数组，每个图片控制在xK以下
 * 
 * @author jackflit
 * 
 */
public class ShootUtils {
	/** 随机字符库 */
	private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final Random random = new Random();

	public static int getRandom(int num) {
		return random.nextInt(num);
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 获得随机的中文字符串
	 * 
	 * @return
	 */
	public static String getRandomChineseChar() {
		try {
			return new String(new byte[] { (Integer.valueOf(176 + getRandom(71 + 1))).byteValue(), //176=B0 D7   0~71
					(Integer.valueOf(161 + getRandom(88 + 1))).byteValue() //161=A1 FE  0~93
					}, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "烫";
		}
	}

	/**
	 * 获得指定长度的中文字符
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomChineseString(int length) {
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append(getRandomChineseChar());
		}
		return buffer.toString();
	}

	/**
	 * 获得随机数
	 * 
	 * @return
	 */
	public static char getRandomNumber() {
		int digit = getRandom(CHARS.length);
		if (digit < 0) {
			digit = 0;
		}
		if (digit >= CHARS.length) {
			digit = CHARS.length - 1;
		}
		return CHARS[digit];

	}

	/**
	 * 获得指定长度随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length) {
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append(getRandomNumber());
		}
		return buffer.toString();
	}

	/**
	 * 获得数字验证图
	 * 
	 * @param numbers
	 * @return
	 */
	public static byte[] getNumberShootImage(String numbers) {
		ByteArrayOutputStream dest = new ByteArrayOutputStream();
		new RegisterCode().create(70, 25, numbers, "宋体", 19,//隶书  楷体_GB2312
				1, 15, dest);
		return dest.toByteArray();

	}

	/**
	 * 获得默认的放挂机验证图数据
	 * 
	 * @param chinese
	 * @return
	 */
	public static byte[] getDefaultShootImage(String chinese) {
		ByteArrayOutputStream dest = new ByteArrayOutputStream();
		new RegisterCode().create(150, 50, RegisterCode.insertSpace(chinese, 1), "宋体", 24,//隶书  楷体_GB2312
				1 + ShootUtils.getRandom(30), 33, dest);
		return dest.toByteArray();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			String chinese = getRandomChineseString(4);
			FileOutputStream fout = null;
			try {
				fout = new FileOutputStream(chinese + ".jpg");
				fout.write(getDefaultShootImage(chinese));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fout != null) {
					try {
						fout.close();
					} catch (IOException e) {
					}
				}
			}

		}

	}
}
