package com.zengyu;

import com.zengyu.QRException.InvalidColorException;
import com.zengyu.QRException.InvalidLengthException;
import com.zengyu.QRException.InvalidPathException;

public class QRUtils implements IQR {
	private String content = "";
	private String outputPath = "";
	private String logoPath = "";
	private int qrcodeSize = 400;
	private int qrcodeColor = 0xff000000;

	@Override
	public void encode() {

	}

	@Override
	public void encode(String content) {
		encode(content, null, null, null, null);
	}

	@Override
	public void encode(String content, String outputPath) {
		encode(content, outputPath, null, null, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath) {
		encode(content, outputPath, logoPath, null, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath, String qrcodeSize) {
		encode(content, outputPath, logoPath, qrcodeSize, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor) {
		try {
			formatParams(content, outputPath, logoPath, qrcodeSize, qrcodeColor);
			encode();
		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (InvalidLengthException e) {
			e.printStackTrace();
		} catch (InvalidColorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void formatParams(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor)
			throws InvalidPathException, InvalidLengthException, InvalidColorException {
		if (outputPath != null) {
			if (outputPath.trim() == "") {
				throw new InvalidPathException("The output path can't be empty string.");
			} else {
				this.outputPath = outputPath;
			}
		}
		if (logoPath != null) {
			if (logoPath.trim() == "") {
				throw new InvalidPathException("The logo path can't be empty string.");
			} else {
				this.logoPath = logoPath;
			}
		}
		if (qrcodeSize != null) {
			try {
				int size = Integer.valueOf(qrcodeSize);
				if (size == 0) {
					throw new InvalidLengthException("The QR code size can't be zero.");
				} else {
					this.qrcodeSize = size;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (qrcodeColor != null) {
			try {
				int color = Integer.valueOf(qrcodeColor);
				if (color == 0xffffffff) {
					throw new InvalidLengthException("The QR code color can't be white.");
				} else {
					this.qrcodeSize = color;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
