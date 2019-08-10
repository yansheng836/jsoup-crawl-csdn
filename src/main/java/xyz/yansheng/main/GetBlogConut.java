/**
 * @Title GetBlogConut.java
 * @Package xyz.yansheng.main
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 21:14:42
 * @version v1.0
 */
package xyz.yansheng.main;

import xyz.yansheng.utility.BlogUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 21:14:42
 * @version v1.0 
 */
public class GetBlogConut {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 21:14:42
	 * @Description 1.获取博客数量
	 */
	public static void main(String[] args) {
		// 个人主页网址：personal-home-page，如https://me.csdn.net/username
		final String PERSONAL_HOME_PAGE = "https://me.csdn.net/weixin_41287260";
	
		int blogCount = BlogUtil.getBlogCounter(PERSONAL_HOME_PAGE);
		System.out.println("blogCount:" + blogCount);

	}

}
