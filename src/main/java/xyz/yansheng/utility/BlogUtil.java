/**
 * @Title BlogUtil.java
 * @Package xyz.yansheng.utility
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 18:37:58
 * @version v1.0
 */
package xyz.yansheng.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xyz.yansheng.entity.Blog;

/**
 * <p>Title: 爬取博客的工具类</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 18:37:58
 * @version v1.0 
 */
public class BlogUtil {

	/**
	 * @Title getBlogCounter
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 18:54:17
	 * @Description 获取某博主的csdn博客的数量
	 * @param personalHomePage 个人主页网址：personal-home-page，如https://me.csdn.net/username
	 * @return   
	 * int 博客数量
	 */
	public static int getBlogCounter(String personalHomePage) {

		// 1. 获取文档对象
		Document doc = null;
		try {
			doc = Jsoup.connect(personalHomePage).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(doc);

		// 2. 查找包含博客数量的元素
		Element countElement = doc.select("span.count").first();
		//System.out.println("countElement:" + countElement);

		// 3. 取出值
		String blogCount = countElement.text();
		//System.out.println("blogCount:" + blogCount);

		return Integer.parseInt(blogCount);
	}

	/**
	 * @Title getBlogListPage
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 18:58:07
	 * @Description 获取个人博客的页数，一页有20个博客；如果除不尽、有余数，整除博客页面+1
	 * @param blogCount 博客数量
	 * @return   
	 * int 博客页数
	 */
	public static int getBlogListPage(int blogCount) {

		// 向上取整，注意20.0，如果是20就是整数除法了，而不是浮点数除法
		int blogListPage = (int) Math.ceil(blogCount / 20.0);

		return blogListPage;
	}

	/**
	 * @Title getBlogs
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 19:05:04
	 * @Description 返回博客列表
	 * @param blogCount 博客数量
	 * @return   
	 * ArrayList<Blog> 博客列表
	 */
	public static ArrayList<Blog> getBlogs(int blogListPage, String blogHome) {

		// 拼接博客列表页网址 
		// 如列表第二页为：https://blog.csdn.net/weixin_41287260/article/list/2
		final String ARTICLE_LIST = "/article/list/";

		String blogListUrl = null;
		// 用于存放博客列表，设置初始容量为：页面*20
		ArrayList<Blog> blogs = new ArrayList<Blog>(blogListPage * 20);
		for (int i = 1,pageNum = blogListPage+1; i < pageNum; i++) {
			blogListUrl = blogHome + ARTICLE_LIST + Integer.toString(i);

			// 1. 获取文档对象
			Document doc = null;
			try {
				doc = Jsoup.connect(blogListUrl).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(doc);

			// 2. 查找包含博客数量的元素
			Element articleList = doc.select("div.article-list").first();
			// System.out.println("articleList:" + articleList);

			Elements blogInfo = articleList.select("h4>a");
			//System.out.println("blogInfo:" + blogInfo);

			Iterator<Element> iterable = blogInfo.iterator();
			while (iterable.hasNext()) {
				Element element = (Element) iterable.next();
				//				System.out.println(element);
				String aString = element.toString();
				// 如果不含blogHome（https://blog.csdn.net/weixin_41287260），则移除
				// 排除乱入的网址 :https://blog.csdn.net/yoyo_liyy/article/details/82762601
				if (!aString.contains(blogHome)) {
					// System.out.println(element);
					iterable.remove();
				} else {
					String blogUrl = element.attr("href");
					//System.out.println("blogUrl:" + blogUrl);

					// 裁剪博客标题，前面有：原，空格
					String blogTitle = element.text().substring(2);
					//System.out.println("blogTitle:" + blogTitle);

					Blog blog = new Blog();
					blog.setUrl(blogUrl);
					blog.setTitle(blogTitle);
					blogs.add(blog);
				}
			}
		}

		return blogs;
	}

}
