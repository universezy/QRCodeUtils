package com.zengyu;

/**
 * 对外接口
 * 
 * @author Agent ZengYu
 *
 */
public interface IQR {
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
}
