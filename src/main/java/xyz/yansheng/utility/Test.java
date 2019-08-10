/**
 * @Title Test.java
 * @Package xyz.yansheng.utility
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 20:03:05
 * @version v1.0
 */
package xyz.yansheng.utility;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 20:03:05
 * @version v1.0 
 */
public class Test {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-10 20:03:05
	 * @Description TODO
	 * @param args   
	 * void 
	 * @see  
	 * @exception
	 */
	public static void main(String[] args) {
		// 1. 获取文档对象
		Document doc = null;
		try {
			doc = Jsoup.connect("https://blog.csdn.net/weixin_41287260").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(doc);

		// 2. 查找包含博客数量的元素
		Element articleList = doc.select("div.article-list").first();
		// System.out.println("articleList:" + articleList);
		
		Elements articleList1 = doc.select("div.article-item-box.csdn-tracking-statistics");
		// System.out.println("articleList1:" + articleList1);
		
		for (int i = 0; i < articleList1.size(); i++) {
			if (i==0) {
				articleList1.remove(i);
				System.out.println("移除0");
			}
			
		}
		System.out.println("articleList1:" + articleList1);
	}

}
