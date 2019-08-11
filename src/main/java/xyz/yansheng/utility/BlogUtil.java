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

		// 1.获取文档对象
		Document doc = null;
		try {
			doc = Jsoup.connect(personalHomePage).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2.查找包含博客数量的元素
		Element countElement = doc.select("span.count").first();

		// 3.取出元素包含的文本（字符串），这里为博客数量
		String blogCount = countElement.text();

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
	 * @version v1.1
	 * @date 2019-08-10 19:05:04
	 * @Description 返回博客列表
	 * @param blogCount 博客数量
	 * @return   
	 * ArrayList<Blog> 博客列表
	 */
	public static ArrayList<Blog> getBlogs(int blogListPage, String blogHome) {

		// 拼接博客列表页网址 
		// 如列表第二页为：https://blog.csdn.net/weixin_41287260/article/list/2
		final String articleListString = "/article/list/";

		// 用于存放博客列表，设置初始容量为：页面*20
		ArrayList<Blog> blogs = new ArrayList<Blog>(blogListPage * 20);

		// 定义变量：博客列表页的网址
		String blogListUrl = null;
		for (int i = 1, pageNum = blogListPage + 1; i < pageNum; i++) {
			// 拼接网址
			blogListUrl = blogHome + articleListString + Integer.toString(i);

			// 1. 获取文档对象
			Document doc = null;
			try {
				doc = Jsoup.connect(blogListUrl).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(doc);

			// 2. 查找包含博客列表的元素
			Element articleList = doc.select("div.article-list").first();

			// 3. 查找每篇博客的元素
			// 注意这里因为class里面有空格，class="article-item-box csdn-tracking-statistics",
			// 相当于是两个class
			Elements blogInfo = articleList.select("div.article-item-box.csdn-tracking-statistics");
			//System.out.println("blogInfo:" + blogInfo);

			for (int j = 0, size = blogInfo.size(); j < size; j++) {
				// 如果不含blogHome（https://blog.csdn.net/weixin_41287260），则移除
				// 排除乱入的第一个网址 :https://blog.csdn.net/yoyo_liyy/article/details/82762601
				if (j == 0) {
					// blogInfo.remove(j);
					continue;
				}
				// 取出博客信息
				Element h4 = blogInfo.get(j).select("h4>a").first();
				String blogUrl = h4.attr("href");
				//System.out.println("blogUrl:" + blogUrl);

				// 裁剪博客标题，前面有：原，空格
				String blogTitle = h4.text().substring(2);
				//System.out.println("blogTitle:" + blogTitle);

				Element date = blogInfo.get(j).select("span.date").first();
				String blogDate = date.text();
				//System.out.println("blogDate:" + blogDate);

				Blog blog = new Blog();
				blog.setUrl(blogUrl);
				blog.setCreateTime(blogDate);
				blog.setTitle(blogTitle);

				blogs.add(blog);
			}
		}

		return blogs;
	}

	/**
	 * @Title getBlogPictures
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 22:22:34
	 * @Description 获取博客中的图片的链接
	 * @param blogUrl 博客的网址
	 * @return   
	 * ArrayList<String> 博客中的图片的链接
	 */
	public static ArrayList<String> getBlogPictures(String blogUrl) {

		ArrayList<String> picUrls = new ArrayList<String>();

		// 1. 获取文档对象
		Document doc = null;
		try {
			doc = Jsoup.connect(blogUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. 查找包含博客列表的元素
		Elements images = doc.select("img.has");
		for (Element element : images) {
			String picUrl = element.attr("src");
			// 对特殊字符串（如下）进行裁剪
			picUrl = StringUtil.subPicUrl(picUrl);
			picUrls.add(picUrl);
		}

		return picUrls;
	}

}
