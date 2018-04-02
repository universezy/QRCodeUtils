# QRCodeUtils
## About

QRCodeUtils是一个基于[ZXing](https://github.com/zxing/zxing)的二维码工具类，功能包括二维码生成和解析。

---
## Download

[QRCodeUtils-3.3.2.jar](https://github.com/frogfans/QRCodeUtils/raw/master/QRCodeUtils-3.3.2.jar)

---
## API

<table>
  <tr>
    <th width=30%, bgcolor=lightgrey>Method</th>
    <th width=35%, bgcolor=lightgrey>Param</th>
    <th width="35%", bgcolor=lightgrey>Detail</th>
  </tr>
  <tr>
    <td> QRUtils encode(String content)</td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
  </tr>
  <tr>
    <td> QRUtils encode(String content, String outputPath)</td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
  </tr>
  <tr>
    <td>QRUtils encode(String content, String outputPath, String logoPath))</td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
  </tr>
  <tr>
    <td>QRUtils encode(String content, String outputPath, String logoPath, int qrcodeSize)</td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
    <td><ul><li>(<a href="#li5">参考#5</a>)</li></ul></td>
  </tr>
  <tr>
    <td id="li5">QRUtils encode(String content, String outputPath, String logoPath, int qrcodeSize, String qrcodeColor)</td>
    <td><ul><li>content: 二维码内容</li><li>outputPath: 二维码图片输出路径</li><li>logoPath: 二维码标志路径</li><li>qrcodeSize: 二维码尺寸</li><li>qrcodeColor: 二维码颜色，格式为"#"开头的7位RGB字符串"#000000"或者"0xff"开头的10位16进制RGB字符串"0xff000000"</li></ul></td>
    <td><ul><li>content: 为空时抛出NullContentException异常</li><li>outputPath: 为空时不输出二维码图片文件，为空字符串时抛出InvalidPathException异常</li><li>logoPath: 为空时不使用中间的标志图案</li><li>qrcodeSize: 默认边长为400，为0时使用默认边长</li><li>qrcodeColor: 默认颜色为黑色，为白色时抛出InvalidColorException异常，建议使用与白色形成鲜明对比的颜色，否则可能造成无法识别二维码</li><li>返回工具类实例</li></ul></td>
  </tr>
  <tr>
    <td>QRUtils writeToFile(String outputPath)</td>
    <td><ul><li>outputPath: 二维码图片输出路径</li></ul></td>
    <td><ul><li>outputPath: 为空或空字符串时抛出InvalidPathException异常</li><li>返回工具类实例</li></ul></td>
  </tr>
  <tr>
    <td>String decode(String inputPath)</td>
    <td><ul><li>inputPath: 二维码图片输入路径</li></ul></td>
    <td><ul><li>inputPath: 为空或空字符串时抛出InvalidPathException异常</li><li>返回二维码图片解析出的字符串</li></ul></td>
  </tr>
  <tr>
    <td>BufferedImage getQRCodeImage()</td>
    <td></td>
    <td><ul><li>返回二维码图片的BufferedImage实例</li></ul></td>
  </tr>
</table>

---
### Example

- Encode

```java
import com.zengyu.QRUtils;

public class Test {

	public static void main(String[] args) {
		QRUtils utils = new QRUtils();
		utils.encode("https://github.com/frogfans/QRCodeUtils", "/home/zengyu/eclipse-workspace/", "/home/zengyu/eclipse-workspace/logo.jpeg", 0, "#0000ff");
	}
}
```

控制台输出：

```
Output: /home/zengyu/eclipse-workspace/1522634604303.jpg
Encoding successful.
```

二维码图片：

![](https://github.com/frogfans/QRCodeUtils/blob/master/image/1522634604303.jpg?raw=true)

- Decode

```
import com.zengyu.QRUtils;

public class Test {

	public static void main(String[] args) {
		QRUtils utils = new QRUtils();
		utils.decode("/home/zengyu/git/QRCodeUtils/image/1522634604303.jpg");
	}
}

```

控制台输出：

```
Output: https://github.com/frogfans/QRCodeUtils
Decoding successful.

```

---
### Dependency

- [ZXing Core » 3.3.2](http://mvnrepository.com/artifact/com.google.zxing/core/3.3.2)

- [ZXing Java SE Extensions » 3.3.2](http://mvnrepository.com/artifact/com.google.zxing/javase/3.3.2)

---
### Version

- 3.3.2

jar文件名的版本号同ZXing的版本号。

---
### Licence

#### [Apache License 2.0](https://github.com/frogfans/QRCodeUtils/blob/master/LICENSE)

---
### Feedback

[Issues](https://github.com/frogfans/QRCodeUtils/issues)
