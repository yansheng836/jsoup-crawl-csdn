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
	 * @Description 测试BlogUtilTest
	 */
	public static void main(String[] args) {

		// 1.获取博客数量
		// test method: int getBlogCounter(String personalHomePage)
		int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);
		System.out.println("blogCount:" + blogCount);

		// 2.获取博客列表页数
		// test method: int getBlogListPage(int blogCount)
		int blogListPage = BlogUtil.getBlogListPage(blogCount);
		System.out.println("blogListPage:" + blogListPage);

		// 3.获取博客列表
		// test method: ArrayList<Blog> getBlogs(int blogListPage, String blogHome)
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BlogUtil.BLOG_HOME);
		//		for (int i = 0, size = blogs.size(); i < size; i++) {
		//			System.out.println(blogs.get(i).toString());
		//		}

		// 4.获取博客里面的图片的URL
		// test method: ArrayList<String> getBlogPic(String blogUrl)
		// 注：该测试博客文章中只有一张图片
		String blogUrl = "https://blog.csdn.net/weixin_41287260/article/details/97621561";
		ArrayList<String> picUrls = new ArrayList<String>();
		picUrls = BlogUtil.getBlogPictures(blogUrl);
		for (int i = 0, size = picUrls.size(); i < size; i++) {
			System.out.println(picUrls.get(i).toString());
		}
	}

}
