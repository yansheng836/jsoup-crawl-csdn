package xyz.yansheng.main;

import xyz.yansheng.utility.BlogUtil;

/**
 * 获取博客数量
 * 
 * @author yansheng
 * @date 2019/08/10
 */
public class GetBlogCount {

    /**
     * 获取博客数量
     */
    public static void main(String[] args) {

        int blogCount = BlogUtil.getBlogCounter(BlogUtil.PERSONAL_HOME_PAGE);
        System.out.println("blogCount:" + blogCount);

    }

}
