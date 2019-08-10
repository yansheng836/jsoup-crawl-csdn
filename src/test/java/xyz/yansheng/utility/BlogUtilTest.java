/**
 * @Title BlogUtilTest.java
 * @Package xyz.yansheng.utility
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 18:39:37
 * @version v1.0
 */
package xyz.yansheng.utility;

import java.util.ArrayList;

import xyz.yansheng.entity.Blog;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 18:39:37
 * @version v1.0 
 */
public class BlogUtilTest {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 18:39:37
	 * @Description TODO
	 * @param args   
	 * void 
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

		System.out.println("blogCount:" + blogCount);
		System.out.println("blogListPage:" + blogListPage);

		// 3.获取博客列表
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BLOG_HOME);
		for (int i = 0,size = blogs.size(); i < size; i++) {
			System.out.println(blogs.get(i).toString());
		}
	}

}
