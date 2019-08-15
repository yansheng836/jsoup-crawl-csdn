/**
 * @Title TestMdImg.java
 * @Package xyz.yansheng.main
 * @Description TODO
 * @author yansheng
 * @date 2019-08-15 02:04:05
 * @version v1.0
 */
package xyz.yansheng.main;

import java.util.ArrayList;

import xyz.yansheng.utility.BlogUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-15 02:04:05
 * @version v1.0 
 */
public class TestMdImg {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-15 02:04:05
	 * @Description 测试是否能够下载markdown编辑的博客，之前的程序存在问题：如果博客是用markdown写的会失效，后发现是元素标签不一样！
	 */
	public static void main(String[] args) {

		/*
		 * 富文本写的博客：https://blog.csdn.net/weixin_41287260/article/details/92185040
		 * markdown博客：https://blog.csdn.net/weixin_41287260/article/details/99354076
		 */
		String blogUrl1 = "https://blog.csdn.net/weixin_41287260/article/details/92185040";
		String blogUrl2 = "https://blog.csdn.net/weixin_41287260/article/details/99354076";
		
		ArrayList<String> picUrls = BlogUtil.getBlogPictures(blogUrl1);
		for (String url : picUrls) {
			System.out.println("url:" + url);
		}

	}

}
