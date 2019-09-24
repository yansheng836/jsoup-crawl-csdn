package xyz.yansheng.main;

import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;

/**
 * @author yansheng
 * @date 2019/08/10
 */
public class GetBlogs {

	/**
	 * 获取博客列表信息
	 */
	public static void main(String[] args) {

		// 1.获取博客数量
		int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);

		// 2.获取博客列表页数
		int blogListPage = BlogUtil.getBlogListPage(blogCount);

		// 3.获取博客列表
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BlogUtil.BLOG_HOME);
		for (int i = 0, size = blogs.size(); i < size; i++) {
			System.out.println(blogs.get(i).toString());
		}

	}

}
