package xyz.yansheng.utility;

/**
 * @author yansheng
 * @date 2019/08/10
 */
public class FileUtilTest {

	/**
	 * 测试工具类FileUtil
	 */
	public static void main(String[] args) {

		// 1.创建文件夹
		// test method: int mkdir(String dirPath)
		String dirPath = "./new/";
		FileUtil.mkdir(dirPath);

		// 2.下载图片
		// test method: void downloadPic(String picUrl, String dirPath)
		String picUrl = "http://patiencecats.com/ueditor/php/upload/image/20180312/1520829763554937.jpg";
		FileUtil.downloadPic(picUrl, dirPath);

	}

}
