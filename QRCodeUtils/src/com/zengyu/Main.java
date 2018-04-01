package com.zengyu;
import com.zengyu.QRUtils;

public class Main {
	public static void main(String[] args) {
		QRUtils utils = new QRUtils();
		utils.encode("https://www.baidu.com/", "e://", "e://1.jpg", "1000", "#00ff00");
	}
}
