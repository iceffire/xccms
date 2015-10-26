package edu.cqu.fly.xccms.util;

/**
 * 随即图片生成器 
 * @company 点滴工作室
 * @author liuhaihua
 */
import java.awt.*;
import java.awt.image.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.apache.commons.lang.RandomStringUtils;

/**
 * 随即图片生成器 该类用于用户注册时候需要用户根据图片内容进行填写正确后方可注册
 */
public class RandomImageGenerator {
	// 随即生成包含验证码的字符串
	public static String random(int strnum) {
		// 20060320 add by wyx
		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "";
		str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
		return RandomStringUtils.random(strnum, str);// 返回6为的字符串
	}

	/**
	 * 根据要求的数字生成图片,背景为白色,字体大小16,字体颜色黑色粗体
	 * 
	 * @param num
	 *            要生成的数字
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void render(String num, OutputStream out) throws IOException {
		// 设定宽度和高度
		int width = 80;
		int height = 20;
		// 在内存中创建图象
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics2D g = (Graphics2D) bi.getGraphics();
		// 画边框
		java.util.Random random = new java.util.Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 设置字体
		Font mFont = new Font("Tahoma", Font.BOLD, 16);
		g.setFont(mFont);
		g.setColor(Color.BLACK);// 设置字体颜色
		// 画认证码,每个认证码在不同的水平位置
		String str1[] = new String[num.length()];
		for (int i = 0; i < str1.length; i++) {
			str1[i] = num.substring(i, i + 1);
			int w = 0;
			int x = (i + 1) % 3;
			// 随即生成验证码字符的水平偏移量
			if (x == random.nextInt(3)) {
				w = 15 - random.nextInt(3);
			} else {
				w = 15 + random.nextInt(3);
			}
			// 随即生成颜色
			Color color1 = new Color(random.nextInt(180), random.nextInt(180),
					random.nextInt(180));
			g.setColor(color1);
			g.drawString(str1[i], 20 * i + 5, w);
		}
		// 随机产生干扰点,并用不同的颜色表示，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));
			g.setColor(color1); // 随即画各种颜色的点
			g.drawOval(x, y, 0, 0);
		}
		// 画干扰线
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			Color color1 = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));
			g.setColor(color1); // 随即画各种颜色的线
			g.drawLine(x, y, x1, y1);
		}
		// 图像生效
		g.dispose();
		// 输出页面
		ImageIO.write(bi, "jpg", out);
	}

	public static void main(String[] args) throws IOException {
		String num = random(4);
		System.out.println(num);
		render(num, new FileOutputStream("D:\\test.jpg"));
		System.out.println("Image generated.");
	}
}