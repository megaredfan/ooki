package org.kriver.core.common;

/**
 * Copyright 2003-2005 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码
 * 
 * @author jackflit
 * 
 */
public class RegisterCode {

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

	public BufferedImage getBufferedImage(int width, int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public BufferedImage getBufferedImage(InputStream in) throws Exception {
		return ImageIO.read(in);
	}

	public void create(int imageWidth, int imageHeight, String randNumber, String fontType, int fontSize, int x, int y,
			OutputStream out) {
		BufferedImage image = getBufferedImage(imageWidth, imageHeight);
		generate(image, randNumber, fontType, fontSize, x, y, out);
	}

	/**
	 * 生成验证码
	 * 
	 * @param image
	 * @param randNumber
	 * @param fontType
	 * @param fontSize
	 * @param x
	 * @param y
	 * @param out
	 */
	public void generate(BufferedImage image, String randNumber, String fontType, int fontSize, int x, int y,
			OutputStream out) {

		try {
			int width = image.getWidth();
			int height = image.getHeight();

			// 获取图形上下文
			Graphics g = image.getGraphics();

			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			//画边框
			//g.setColor(new Color());
			//g.drawRect(0,0,width-1,height-1);

			// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			//生成随机类
			Random random = new Random();

			switch (random.nextInt(3)) {
			case 0:
				for (int i = 0; i < 20 + random.nextInt(100); i++) {
					int x2 = random.nextInt(width);
					int y2 = random.nextInt(height);
					int x3 = random.nextInt(200);
					int y3 = random.nextInt(100);
					g.drawLine(x2, y2, x2 + x3, y2 + y3);
				}
				break;
			case 1:
				for (int i = 0; i < 20 + random.nextInt(100); i++) {
					g.drawOval(random.nextInt(width), random.nextInt(height), random.nextInt(50), random.nextInt(50));
				}
				break;
			case 2:
				for (int i = 0; i < 20 + random.nextInt(100); i++) {
					g.drawRoundRect(random.nextInt(width), random.nextInt(height), random.nextInt(50), random
							.nextInt(50), random.nextInt(50), random.nextInt(50));
				}
				break;
			}

			// 将认证码显示到图象中 //
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			//g.drawString(randNumber, x, y);
			for (int i = 0; i < randNumber.length(); i++) {
				if (fontList.isEmpty()) {
					g.setFont(new Font(fontType, Font.PLAIN, fontSize));
				} else {
					g.setFont(new Font(fontList.get(random.nextInt(fontList.size())), Font.PLAIN, fontSize));
				}
				g.drawString(randNumber.charAt(i) + "", x + (i * fontSize) + random.nextInt(5) - random.nextInt(5), y
						+ random.nextInt(10));
			}

			switch (random.nextInt(3)) {
			case 0://画长线
				for (int i = 0; i < random.nextInt(10); i++) {
					int x2 = random.nextInt(width);
					int y2 = random.nextInt(height);
					int x3 = random.nextInt(212);
					int y3 = random.nextInt(212);
					g.drawLine(x2, y2, x2 + x3, y2 + y3);
				}
				break;
			case 1://画驼圆
				for (int i = 0; i < random.nextInt(10); i++) {
					g.drawOval(random.nextInt(width), random.nextInt(height), random.nextInt(50), random.nextInt(50));
				}
				break;
			case 2://方
				for (int i = 0; i < random.nextInt(10); i++) {
					g.drawRoundRect(random.nextInt(width), random.nextInt(height), random.nextInt(50), random
							.nextInt(50), random.nextInt(50), random.nextInt(50));
				}
				break;
			}

			// 图象生效
			g.dispose();

			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 可用字体列表
	 */
	private final static java.util.List<String> fontList = new ArrayList<String>();
	static {
		if (fontList.isEmpty()) {
			fontList.add("SimSun");
			fontList.add("KaiTi_GB2312");
			fontList.add("YouYuan");
			fontList.add("SimHei");
			fontList.add("LiSu");
		}
	}

	/**
	 * 获得依赖的字体
	 * 
	 * @return
	 */
	public static final java.util.List<String> getFontList() {
		return fontList;
	}

	/**
	 * 将insnum个空格随机插入k中间
	 * 
	 * @param k
	 * @param inSnum
	 * @return
	 */
	public static final String insertSpace(String k, int inSnum) {
		StringBuffer o = new StringBuffer();
		for (int i = 0; i < k.length(); i++) {
			o.append(k.charAt(i)).append(_getSpace(ShootUtils.getRandom(inSnum)));
		}
		return o.toString();
	}

	/**
	 * 获得空格
	 * 
	 * @param num
	 * @return
	 */
	public static final StringBuffer _getSpace(int num) {
		StringBuffer o = new StringBuffer(num);
		for (int i = 0; i < num; i++) {
			o.append(" ");
		}
		return o;
	}

}
