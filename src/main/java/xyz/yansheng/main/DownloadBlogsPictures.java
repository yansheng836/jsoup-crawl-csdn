/**
 * @Title DownloadBlogsPictures.java
 * @Package xyz.yansheng.main
 * @Description TODO
 * @author yansheng
 * @date 2019-08-11 02:05:34
 * @version v1.0
 */
package xyz.yansheng.main;

import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;
import xyz.yansheng.utility.FileUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-11 02:05:34
 * @version v1.0 
 */
public class DownloadBlogsPictures {

	// 定义常量字符串
	/**  
	 * @Fields PERSONAL_HOME_PAGE : 个人主页网址：personal-home-page，如https://me.csdn.net/username
	 */
	public static final String PERSONAL_HOME_PAGE = "https://me.csdn.net/weixin_41287260";

	/**  
	 * @Fields BLOG_HOME : 个人博客主页：personal-blog-page，如https://blog.csdn.net/username
	 */
	public static final String BLOG_HOME = "https://blog.csdn.net/weixin_41287260";

	/**  
	 * @Fields PARENT_PATH : 保存博客图片的根路径
	 */
	public static final String PARENT_PATH = "E://2CSDN//";

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.1
	 * @date 2019-08-11 02:05:34
	 * @Description 备份博客的图片
	 */
	public static void main(String[] args) {
		// 1.获取博客数量
		int blogCount = BlogUtil.getBlogCounter(PERSONAL_HOME_PAGE);

		// 2.获取博客列表页数
		int blogListPage = BlogUtil.getBlogListPage(blogCount);

		// 3.获取博客列表
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BLOG_HOME);

		ArrayList<String> picUrls = new ArrayList<String>();

		// 这里仅仅是为了测试，所以控制循环变量，只下载这篇博客的图片
		for (int i = 0, blogNo = blogs.size(); i < blogNo; i++) {
			Blog blog = blogs.get(i);
			System.out.print("第" + Integer.toString(i + 1) + "篇博客-图片");
			// 创建文件夹，以博客的创建时间为文件夹名
			String dirPath = PARENT_PATH + blog.getCreateTime().substring(0, 10) + "-" + blog.getTitle()
					+ "//";

			// 判断创建文件夹的返回值，如果是0，即已存在，则认为已下载该博客的图片，跳出该循环；
			// 如果是-1，则文件夹创建失败，故路径错误，不可能成功保存图片，也直接跳出循环。
			int result = FileUtil.mkdir(dirPath);
			if (result == 0 || result == -1) {
				continue;
			}

			// 获取该博客的所有图片的url列表
			picUrls = BlogUtil.getBlogPictures(blog.getUrl());

			// 下载图片
			for (int j = 0, picNo = picUrls.size(); j < picNo; j++) {
				String picUrl = picUrls.get(j);
				FileUtil.downloadPic(picUrl, dirPath);
				if (j == picNo - 1) {
					System.out.println(" -已下载" + picNo + "张图片\n");
				}
			}
		}

	}

}
