/**
 * @Title GetBlogsPictures.java
 * @Package xyz.yansheng.main
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 22:24:42
 * @version v1.0
 */
package xyz.yansheng.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;
import xyz.yansheng.utility.FileUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 22:24:42
 * @version v1.0 
 */
public class GetBlogsPictures {

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
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 22:24:42
	 * @Description TODO
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
		for (int i = 0, size = blogs.size(); i < 1; i++) {
			Blog blog = blogs.get(i);
			// 创建文件夹，以博客的创建时间为文件夹名
			String dirPath = "./" + blog.getCreateTime().substring(0, 10) + "/";
			FileUtil.mkdir(dirPath);

			picUrls = BlogUtil.getBlogPictures(blog.getUrl());

			// 下载图片
			for (int j = 0, len = picUrls.size(); i < len; i++) {
				String picUrl = picUrls.get(i);
				FileUtil.downloadPic(picUrl, dirPath);
			}
		}

	}

}