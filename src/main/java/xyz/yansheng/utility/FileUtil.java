/**
 * @Title FileUtil.java
 * @Package xyz.yansheng.utility
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 22:36:23
 * @version v1.0
 */
package xyz.yansheng.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.select.Elements;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 22:36:23
 * @version v1.0 
 */
public class FileUtil {

	/**
	 * @Title mkdir
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 22:46:14
	 * @Description 创建文件夹
	 * @param dirPath
	 * @return   
	 * int 
	 */
	public static int mkdir(String dirPath) {

		int reslut = 0;

		File dirFile = new File(dirPath);
		if (!dirFile.exists()) {
			if (dirFile.mkdir()) {
				System.out.println("创建文件夹：" + dirPath + "成功");
				reslut = 1;
			} else {
				System.out.println("创建文件夹：" + dirPath + "失败");
				reslut = -1;
			}
		} else {
			System.out.println("文件夹：" + dirPath + "已存在");
			reslut = 0;
		}
		return reslut;
	}

	public static void downloadPic(String picUrl, String dirPath) {
		// String path = "";
		URL url = null;

		// 创建输入输出流
		InputStream inputStream = null;
		OutputStream outputStream = null;
		// 建立一个网络链接
		HttpURLConnection con = null;

		// 构造输出文件名（图片名）：路径+链接中的图片名
		// 对图片名进行裁剪：取得最后一个/后面的内容
		// http://patiencecats.com/ueditor/php/upload/image/20180312/1520829763554937.jpg
		int index = picUrl.lastIndexOf('/');
		String outPic = dirPath + picUrl.substring(index + 1, picUrl.length());
		File outFile = new File(outPic);

		try {
			url = new URL(picUrl);
			con = (HttpURLConnection) url.openConnection();
			inputStream = con.getInputStream();
			outputStream = new FileOutputStream(outFile);
			int n = -1;
			byte b[] = new byte[1024];
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);
			}
			outputStream.flush();
			System.out.println("下载图片:" + outPic + "成功！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
