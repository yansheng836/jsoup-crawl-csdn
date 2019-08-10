/**
 * @Title GetBlogs.java
 * @Package xyz.yansheng.jsoup_crawl_csdn
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 21:16:30
 * @version v1.0
 */
package xyz.yansheng.jsoup_crawl_csdn;

import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 21:16:30
 * @version v1.0 
 */
public class GetBlogs {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 21:16:30
	 * @Description 获取博客列表信息
	 */
	public static void main(String[] args) {
		// 个人主页网址：personal-home-page，如https://me.csdn.net/username
		final String PERSONAL_HOME_PAGE = "https://me.csdn.net/weixin_41287260";
		// 个人博客主页：personal-blog-page，如https://blog.csdn.net/username
		final String BLOG_HOME = "https://blog.csdn.net/weixin_41287260";

		// 1.获取博客数量
		int blogCount = BlogUtil.getBlogCounter(PERSONAL_HOME_PAGE);

		// 2.获取博客列表页数
		int blogListPage = BlogUtil.getBlogListPage(blogCount);

		// 3.获取博客列表
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BLOG_HOME);
		for (int i = 0, size = blogs.size(); i < size; i++) {
			System.out.println(blogs.get(i).toString());
		}

	}

}
