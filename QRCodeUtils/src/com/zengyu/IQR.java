package com.zengyu;

import java.awt.image.BufferedImage;

/**
 * 对外接口
 * 
 * @author Agent ZengYu
 *
 */
public interface IQR {
	BufferedImage getQrcodeImage();
	
	QRUtils encode(String content);

	QRUtils encode(String content, String outputPath);

	QRUtils encode(String content, String outputPath, String logoPath);

	QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize);

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
	 */
	QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor);
}
