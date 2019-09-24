package xyz.yansheng.main;

import java.util.ArrayList;

import xyz.yansheng.utility.BlogUtil;

/**
 * @author yansheng
 * @date 2019/08/15
 */
public class TestMdImg {

    /**
     * @param 测试是否能够下载markdown编辑的博客，之前的程序存在问题：如果博客是用markdown写的会失效，后发现是元素标签不一样！
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
