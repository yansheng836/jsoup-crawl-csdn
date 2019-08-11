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
import java.net.MalformedURLException;
import java.net.URL;

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
	 * int 文件夹存在返回0，文件夹创建成功过返回1，文件夹创建失败过返回-1
	 */
	public static int mkdir(String dirPath) {

		int reslut = 0;

		File dirFile = new File(dirPath);
		if (!dirFile.exists()) {
			if (dirFile.mkdir()) {
				System.out.println("创建文件夹：" + dirPath + " 成功");
				reslut = 1;
			} else {
				System.err.println("创建文件夹：" + dirPath + " 失败");
				reslut = -1;
			}
		} else {
			System.out.println("文件夹：" + dirPath + " 已存在");
			reslut = 0;
		}
		return reslut;
	}

	/**
	 * @Title downloadPic
	 * @author yansheng
	 * @version v1.2
	 * @date 2019-08-11 00:54:26
	 * @Description 下载图片
	 * @param picUrl 图片链接
	 * @param dirPath 图片的保存目录
	 */
	public static void downloadPic(String picUrl, String dirPath) {

		/*构造输出文件名（图片名）：路径+链接中的图片名,对图片名进行裁剪：取得最后一个/后面的内容
		 * http://patiencecats.com/ueditor/php/upload/image/20180312/1520829763554937.jpg
		 * 1520829763554937.jpg
		 * 
		 * 如果该文件名不包含后缀，直接在后面加上".jpg"
		 */
		int index = picUrl.lastIndexOf('/');
		// 取得图片文件名
		String picName = picUrl.substring(index + 1, picUrl.length());

		// 对图片文件名进行处理
		picName = StringUtil.checkPicName(picName);

		String outPicPath = dirPath + picName;
		File outFile = new File(outPicPath);
		// 如果图片已存在，则直接跳过下载该图片，因为没有必要再下载一次
		if (outFile.exists()) {
			System.out.println(" -图片：" + outPicPath + " 已存在，故不再下载。");
			return ;
		}

		// 创建URL对象，将字符串解析为URL
		URL url = null;
		// 建立一个网络链接对象
		HttpURLConnection con = null;
		try {
			url = new URL(picUrl);
			con = (HttpURLConnection) url.openConnection();
			//设置请求方式
			con.setRequestMethod("GET");
			//连接
			con.connect();
			//得到响应码
			int responseCode = con.getResponseCode();
			// 这里假设只要不是4xx（请求错误）,5xx（服务器错误）都表示可以下载图片
			if (responseCode < 400) {
				// 响应成功，可以建立连接
			} else {
				System.err.println("图片链接(" + picUrl + ")无效！响应状态码为：" + responseCode);
				return;
			}
		} catch (MalformedURLException e2) {
			System.err.println("图片链接(" + picUrl + ")中不含有合法的网络协议或者无法解析该字符串！");
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 利用jdk1.7的新特性 ：try(resource){……} catch{……}，自动释放资源
		// 1.创建输入输出流  2.建立一个网络链接
		try (InputStream inputStream = con.getInputStream();
				OutputStream outputStream = new FileOutputStream(outFile);) {
			int n = -1;
			byte b[] = new byte[1024];
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);
			}
			outputStream.flush();
			System.out.println(" --下载图片:" + picUrl + " 成功！保存位置为：" + outPicPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
