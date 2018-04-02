package com.zengyu;

/**
 * 二维码异常类
 * 
 * @author zengyu
 *
 */
public class QRException {
	/**
	 * 空内容异常
	 */
	static class NullContentException extends RuntimeException {
		private static final long serialVersionUID = 4527325632148649544L;

		public NullContentException(String s) {
			super(s);
		}
	}

	/**
	 * 路径无效异常
	 */
	static class InvalidPathException extends RuntimeException {
		private static final long serialVersionUID = 5198180599267759598L;

		public InvalidPathException(String s) {
			super(s);
		}
	}

	/**
	 * 颜色无效异常
	 */
	static class InvalidColorException extends RuntimeException {
		private static final long serialVersionUID = -8701028977791094270L;

		public InvalidColorException(String s) {
			super(s);
		}
	}

	/**
	 * 文件未找到异常
	 */
	static class FileNotFoundException extends RuntimeException {
		private static final long serialVersionUID = -854623138487729123L;

		public FileNotFoundException(String s) {
			super(s);
		}
	}
}
