/**
 * @Title GetBlogCount.java
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
public class GetBlogCount {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 21:14:42
	 * @Description 1.获取博客数量
	 */
	public static void main(String[] args) {
		
		int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);
		System.out.println("blogCount:" + blogCount);

	}

}
