package xyz.yansheng.main;

import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;
import xyz.yansheng.utility.FileUtil;

/**
 * @author yansheng
 * @date 2019/08/10
 */
public class GetBlogsPictures {

    public static void main(String[] args) {

        // 1.获取博客数量
        int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);

        // 2.获取博客列表页数
        int blogListPage = BlogUtil.getBlogListPage(blogCount);

        // 3.获取博客列表
        ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BlogUtil.BLOG_HOME);

        ArrayList<String> picUrls = new ArrayList<String>();

        int blogSize = blogs.size();
        int picUrlSize = picUrls.size();
        // 这里仅仅是为了测试，所以控制循环变量为1，只下载这篇博客的图片
        for (int i = 0; i < 1; i++) {
            Blog blog = blogs.get(i);
            // 创建文件夹，以博客的创建时间为文件夹名
            String dirPath = "./" + blog.getCreateTime().substring(0, 10) + "/";
            FileUtil.mkdir(dirPath);

            picUrls = BlogUtil.getBlogPictures(blog.getUrl());

            // 下载图片
            for (int j = 0; i < picUrlSize; i++) {
                String picUrl = picUrls.get(i);
                FileUtil.downloadPic(picUrl, dirPath);
            }
        }

    }

}
