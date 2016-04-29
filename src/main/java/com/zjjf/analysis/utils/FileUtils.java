package com.zjjf.analysis.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FileUtils {

	public static String createQRcode() {

		String sRand = "";
		int width = 120;
		int height = 35;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage类的对象
		Graphics g = image.getGraphics(); // 创建Graphics类的对象
		Graphics2D g2d = (Graphics2D) g; // 通过Graphics类的对象创建一个Graphics2D类的对象
		Random random = new Random(); // 实例化一个Random对象
		Font mFont = new Font("华文宋体", Font.BOLD, 30); // 通过Font构造字体
		g.setColor(getRandColor(200, 250)); // 改变图形的当前颜色为随机生成的颜色
		g.fillRect(0, 0, width, height); // 绘制一个填色矩形
		// 画一条折线
		BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 创建一个供画笔选择线条粗细的对象
		g2d.setStroke(bs); // 改变线条的粗细
		g.setColor(Color.DARK_GRAY); // 设置当前颜色为预定义颜色中的深灰色
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		for (int j = 0; j < 3; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
		}
		g.drawPolyline(xPoints, yPoints, 3);
		// 生成并输出随机的验证文字
		g.setFont(mFont);

		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) {
				itmp = random.nextInt(26) + 65; // 生成A~Z的字母
			} else {
				itmp = random.nextInt(10) + 48; // 生成0~9的数字
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			g.drawString(String.valueOf(ctmp), 30 * i + 10, 25);

		}
		g.dispose();
		return sRand;
	}

	private static Color getRandColor(int a, int b) {
		Random random = new Random();
		Color color = new Color(50 + random.nextInt(110), a, b);
		return color;
	}
}
