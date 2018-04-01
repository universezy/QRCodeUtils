package com.zengyu;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zengyu.QRException.InvalidColorException;
import com.zengyu.QRException.InvalidLengthException;
import com.zengyu.QRException.InvalidPathException;
import com.zengyu.QRException.NullContentException;

public class QRUtils implements IQR {
	// 二维码内容
	private String content = "";
	
	// 输出路径
	private String outputPath = "";
	
	// 标志路径
	private String logoPath = "";
	
	// 标志边长，为二维码边长的1/5
	private int logoSize = 80;
	
	// 二维码边长，默认400
	private int qrcodeSize = 600;
	
	// 二维码边距，为二维码边长的1/100
	private int qrcodeMargin = 4;
	
	// 二维码颜色，默认黑色
	private int qrcodeColor = 0xff000000;
	
	// 背景颜色，默认白色
	private static final int WHITE = 0xFFFFFFFF;
	
	// 输出文件格式
	private static final String FORMAT = "jpg";
	
	// 二维码图片
	private BufferedImage qrcodeImage;

	/**
	 * 生成过程
	 */
	private QRUtils encode() {
		// 设置编码参数
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);
		hints.put(EncodeHintType.MARGIN, qrcodeMargin);
		try {
			// 生成二维码矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrcodeSize, qrcodeSize,
					hints);
			int qrcodeWidth = bitMatrix.getWidth();
			int qrcodeHeight = bitMatrix.getHeight();
			// 生成二维码图片
			qrcodeImage = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < qrcodeWidth; x++) {
				for (int y = 0; y < qrcodeHeight; y++) {
					qrcodeImage.setRGB(x, y, (bitMatrix.get(x, y) ? qrcodeColor : WHITE));
				}
			}
			// 判断是否插入标志
			if (logoPath != "") {
				File logoFile = new File(logoPath);
				if (logoFile.exists()) {
					Image logoImgSrc = ImageIO.read(new File(logoPath));
					// 压缩标志
					int width = logoImgSrc.getWidth(null);
					int height = logoImgSrc.getHeight(null);
					if (width > logoSize) {
						width = logoSize;
					}
					if (height > logoSize) {
						height = logoSize;
					}
					Image logoImg = logoImgSrc.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					// 绘制标志
					BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics graphics = bufferedImage.getGraphics();
					graphics.drawImage(logoImg, 0, 0, null);
					graphics.dispose();
					logoImgSrc = logoImg;
					// 插入标志
					Graphics2D graph = qrcodeImage.createGraphics();
					int x = (qrcodeSize - width) / 2;
					int y = (qrcodeSize - height) / 2;
					graph.drawImage(logoImgSrc, x, y, width, height, null);
					Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
					graph.setStroke(new BasicStroke(3f));
					graph.draw(shape);
					graph.dispose();
				}
			}
			// 输出二维码
			if (outputPath != "") {
				String outputName = "" + new Date().getTime() + ".jpg";
				ImageIO.write(qrcodeImage, FORMAT, new File(outputPath + outputName));
				System.out.println("Output: " + outputPath + outputName);
			}
			System.out.println("Encoding successful.");
			return this;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Encoding failed.");
		return null;
	}

	@Override
	public BufferedImage getQrcodeImage() {
		return qrcodeImage;
	}

	@Override
	public QRUtils encode(String content) {
		return encode(content, null, null, null, null);
	}

	@Override
	public QRUtils encode(String content, String outputPath) {
		return encode(content, outputPath, null, null, null);
	}

	@Override
	public QRUtils encode(String content, String outputPath, String logoPath) {
		return encode(content, outputPath, logoPath, null, null);
	}

	@Override
	public QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize) {
		return encode(content, outputPath, logoPath, qrcodeSize, null);
	}

	@Override
	public QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor) {
		try {
			if (formatParams(content, outputPath, logoPath, qrcodeSize, qrcodeColor)) {
				return encode();
			}
		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (InvalidLengthException e) {
			e.printStackTrace();
		} catch (InvalidColorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化参数
	 * 
	 * @param content
	 * @param outputPath
	 * @param logoPath
	 * @param qrcodeSize
	 * @param qrcodeColor
	 * @throws NullContentException
	 *             空内容异常
	 * @throws InvalidPathException
	 *             路径无效异常
	 * @throws InvalidLengthException
	 *             长度无效异常
	 * @throws InvalidColorException
	 *             颜色无效异常
	 */
	private boolean formatParams(String content, String outputPath, String logoPath, String qrcodeSize,
			String qrcodeColor)
			throws NullContentException, InvalidPathException, InvalidLengthException, InvalidColorException {
		if (content == null || content.trim() == "") {
			throw new NullContentException("The content can't be empty.");
		} else {
			this.content = content;
		}
		if (outputPath != null) {
			if (outputPath.trim() == "") {
				throw new InvalidPathException("The output path can't be empty string.");
			} else {
				this.outputPath = outputPath.trim();
			}
		}
		if (logoPath != null) {
			if (logoPath.trim() == "") {
				throw new InvalidPathException("The logo path can't be empty string.");
			} else {
				this.logoPath = logoPath.trim();
			}
		}
		if (qrcodeSize != null) {
			try {
				int size = Integer.valueOf(qrcodeSize);
				if (size == 0) {
					throw new InvalidLengthException("The QR code size can't be zero.");
				} else {
					this.qrcodeSize = size;
					this.logoSize = size / 5;
					this.qrcodeMargin = size / 100;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (qrcodeColor != null) {
			try {
				int color = 0;
				if (qrcodeColor.length() == 7) {
					color = Integer.parseInt(qrcodeColor.substring(1), 16);
				} else if (qrcodeColor.length() == 10) {
					color = Integer.parseInt(qrcodeColor.substring(4), 16);
				} else {
					throw new InvalidColorException("Invalid rgb string.");
				}
				if (color == 0xffffffff) {
					throw new InvalidColorException("The QR code color can't be white.");
				} else {
					this.qrcodeColor = color;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
