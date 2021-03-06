package xyz.yansheng.utility;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xyz.yansheng.entity.Blog;

/**
 * @author yansheng
 * @date 2019/08/10
 */
public class BlogUtil {

    // 定义常量字符串
    /**
     * @Fields PERSONAL_HOME_PAGE : 个人主页网址：personal-home-page，如https://me.csdn.net/username
     */
    public static final String PERSONAL_HOME_PAGE = "https://me.csdn.net/weixin_41287260";

    /**
     * @Fields BLOG_HOME : 个人博客主页：personal-blog-page，如https://blog.csdn.net/username
     */
    public static final String BLOG_HOME = "https://blog.csdn.net/weixin_41287260";

    /**
     * @Fields PARENT_PATH : 保存博客图片的根路径
     */
    public static final String PARENT_PATH = "E://2CSDN//";

    /**
     * 获取某博主的csdn博客的数量
     * 
     * @param personalHomePage
     *            个人主页网址：personal-home-page，如https://me.csdn.net/username
     * @return int 博客数量
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
     * 获取个人博客的页数，一页有20个博客；如果除不尽、有余数，整除博客页面+1
     * 
     * @param blogCount
     *            博客数量
     * @return int 博客页数
     */
    public static int getBlogListPage(int blogCount) {

        // 向上取整，注意20.0，如果是20就是整数除法了，而不是浮点数除法
        int blogListPage = (int)Math.ceil(blogCount / 20.0);

        return blogListPage;
    }

    /**
     * @param blogListPage
     *            博客列表页数
     * @param blogHome
     *            博客主页网址
     * @return ArrayList<Blog> 博客列表
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
            // System.out.println(doc);

            // 2. 查找包含博客列表的元素
            Element articleList = doc.select("div.article-list").first();

            // 3. 查找每篇博客的元素
            // 注意这里因为class里面有空格，class="article-item-box csdn-tracking-statistics",
            // 相当于是两个class
            Elements blogInfo = articleList.select("div.article-item-box.csdn-tracking-statistics");

            for (int j = 0, size = blogInfo.size(); j < size; j++) {
                // 如果不含blogHome（https://blog.csdn.net/weixin_41287260），则移除(或者直接跳出该重循环)
                // 排除乱入的第一个网址 :https://blog.csdn.net/yoyo_liyy/article/details/82762601
                if (j == 0) {
                    // blogInfo.remove(j);
                    continue;
                }
                // 取出博客信息
                Element h4 = blogInfo.get(j).select("h4>a").first();
                String blogUrl = h4.attr("href");

                // 裁剪博客标题，前面有：原，空格
                String blogTitle = h4.text().substring(2);

                Element date = blogInfo.get(j).select("span.date").first();
                String blogDate = date.text();

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
     * 获取博客中的图片的链接
     * 
     * @param blogUrl
     *            博客的网址
     * @return ArrayList<String> 博客中的图片的链接
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

        /*2. 先判断该文章是用富文本写的，还是用markdown写的，因为他们的图片的标签有些差别
         * <article class="baidu_pl">
         * 	 <!-- 富文本写的博客，注意不同之处在这里，类不同 -->
         * 	<div id="content_views" class="htmledit_views" >
         * 
         * 	<!-- markdown写的博客，注意不同之处在这里 -->
         * 	<div id="content_views" class="markdown_views prism-kimbie-light">
         * 
         */
        Element article = doc.selectFirst("article.baidu_pl");
        Element contentViews = article.selectFirst("div#content_views");
        String contentViewsClass = contentViews.attr("class");

        // 进行判断：如果类包含htmledit_views，说明是富文本编辑器写的；否则是markdown写的
        // 3.查找该篇博客中的所有图片
        String htmleditViews = "htmledit_views";
        Elements images = null;
        if (contentViewsClass.contains(htmleditViews)) {
            images = article.select("img.has");
        } else {
            images = article.select("img");
        }

        for (Element element : images) {
            String picUrl = element.attr("src");
            // 对特殊字符串（如下）进行裁剪
            picUrl = StringUtil.subPicUrl(picUrl);
            picUrls.add(picUrl);
        }

        return picUrls;
    }

}
