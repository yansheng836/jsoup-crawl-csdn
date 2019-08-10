/**
 * @Title CrawlCsdn.java
 * @Package xyz.yansheng.jsoup_crawl_csdn
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 18:45:34
 * @version v1.0
 */
package xyz.yansheng.jsoup_crawl_csdn;

import xyz.yansheng.utility.BlogUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 18:45:34
 * @version v1.0 
 */
public class CrawlCsdn {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 18:45:35
	 * @Description 获取博客的
	 */
	public static void main(String[] args) {
		// 个人主页网址：personal-home-page，如https://me.csdn.net/username
		final String PERSONAL_HOME_PAGE = "https://me.csdn.net/weixin_41287260";
		// 个人博客主页：personal-blog-page，如https://blog.csdn.net/username
		final String BLOG_HOME = "https://blog.csdn.net/weixin_41287260";
		
		// 博客数量
		int blogCount = BlogUtil.getBlogCounter(PERSONAL_HOME_PAGE);
		// 博客列表页数
		int blogListPage = BlogUtil.getBlogListPage(blogCount);

		System.out.println("blogCount:" + blogCount);
		System.out.println("blogListPage:" + blogListPage);
		
		

	}

}
