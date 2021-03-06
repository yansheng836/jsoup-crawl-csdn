package xyz.yansheng.main;

import java.io.File;
import java.util.ArrayList;

import xyz.yansheng.entity.Blog;
import xyz.yansheng.utility.BlogUtil;
import xyz.yansheng.utility.FileUtil;
import xyz.yansheng.utility.StringUtil;

/**
 * @author yansheng
 * @date 2019/08/11
 */
public class DownloadBlogsPictures {

	/**
	 * 备份博客的图片
	 */
	public static void main(String[] args) {
		// 1.获取博客数量
		int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);

		// 2.获取博客列表页数
		int blogListPage = BlogUtil.getBlogListPage(blogCount);

		// 3.获取博客列表
		ArrayList<Blog> blogs = BlogUtil.getBlogs(blogListPage, BlogUtil.BLOG_HOME);

		ArrayList<String> picUrls = new ArrayList<String>();

		// 首先保证保存图片的父目录是存在的，如果不存在就创建
		File parentPath = new File(BlogUtil.PARENT_PATH);
		if (!parentPath.exists()) {
			if (parentPath.mkdirs()) {
				System.out.println("创建保存图片的跟目录：'" + parentPath + "' 成功。");
			}else {
				System.err.println("创建保存图片的跟目录：'" + parentPath + "' 失败。");
				return ;
			}
		} else {
			System.out.println("保存图片的跟目录：'" + parentPath + "' 已存在。");
		}

		// 下载所有博客的图片
		for (int i = 0, blogNo = blogs.size(); i < blogNo; i++) {
			Blog blog = blogs.get(i);
			System.out.print("第" + Integer.toString(i + 1) + "篇博客-图片");

			// 创建文件夹，以博客的创建时间+博客标题为文件夹名，如：2018-10-15-html表单提交问题
			String blogTitle = blog.getTitle();
			// 替换文件名中的特殊字符，使能够成功创建该文件夹
			blogTitle = StringUtil.replaceSpecialCharacters(blogTitle);

			String dirPath = BlogUtil.PARENT_PATH + blog.getCreateTime().substring(0, 10) + "-" + blogTitle
					+ "//";

			// 判断创建文件夹的返回值，如果是0，即已存在，则认为已下载该博客的图片，跳出该循环；
			// 如果是-1，则文件夹创建失败，故路径错误，不可能成功保存图片，也直接跳出循环。
			int result = FileUtil.mkdir(dirPath);
			if (result == 0 || result == -1) {
				// continue;
			}

			// 获取该博客的所有图片的url列表
			picUrls = BlogUtil.getBlogPictures(blog.getUrl());

			// 下载图片
			for (int j = 0, picNo = picUrls.size(); j < picNo; j++) {
				String picUrl = picUrls.get(j);
				// 暂时注释，用于测试图片保存的文件名
				FileUtil.downloadPic(picUrl, dirPath);
				if (j == picNo - 1) {
					System.out.println(" -已下载" + picNo + "张图片\n");
				}
				// System.out.println("图片文件名：" + picUrl);
			}
		}

		// 结束语
		System.out.println("\n****已成功下载博主：" + BlogUtil.BLOG_HOME + "所有博客中的图片！****");

	}

}
