package com.zengyu;

import java.awt.image.BufferedImage;

/**
 * 对外接口
 * 
 * @author zengyu
 *
 */
public interface IQR {
	BufferedImage getQRCodeImage();

	QRUtils encode(String content);

	QRUtils encode(String content, String outputPath);

	QRUtils encode(String content, String outputPath, String logoPath);

	QRUtils encode(String content, String outputPath, String logoPath, int qrcodeSize);

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码内容
	 * @param outputPath
	 *            输出路径
	 * @param logoPath
	 *            标志路径
	 * @param qrcodeSize
	 *            二维码边长
	 * @param qrcodeColor
	 *            二维码颜色
	 * @return 返回工具类本身
	 */
	QRUtils encode(String content, String outputPath, String logoPath, int qrcodeSize, String qrcodeColor);

	/**
	 * 输出文件
	 * 
	 * @param outputPath
	 *            输出路径
	 * @return 返回工具类本身
	 */
	QRUtils writeToFile(String outputPath);

	/**
	 * 解析二维码
	 * 
	 * @param inputPath
	 *            输入路径
	 * @return 解析出的字符串
	 */
	String decode(String inputPath);
}
