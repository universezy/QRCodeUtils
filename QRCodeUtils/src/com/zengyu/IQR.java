package com.zengyu;

import com.zengyu.QRException.InvalidColorException;
import com.zengyu.QRException.InvalidLengthException;
import com.zengyu.QRException.InvalidPathException;

public interface IQR {
	void encode();

	void encode(String content);

	void encode(String content, String outputPath);

	void encode(String content, String outputPath, String logoPath);

	void encode(String content, String outputPath, String logoPath, String qrcodeSize);

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
	void encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor);

	/**
	 * 格式化参数
	 * 
	 * @param content
	 * @param outputPath
	 * @param logoPath
	 * @param qrcodeSize
	 * @param qrcodeColor
	 * @throws InvalidPathException
	 *             无效路径异常
	 * @throws InvalidLengthException
	 *             无效长度异常
	 * @throws InvalidColorException
	 *             无效颜色异常
	 */
	void formatParams(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor)
			throws InvalidPathException, InvalidLengthException, InvalidColorException;
}
