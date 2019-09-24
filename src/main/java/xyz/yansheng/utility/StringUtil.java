package xyz.yansheng.utility;

/**
 * @author yansheng
 * @date 2019/08/11
 */
public class StringUtil {

    /**
     * 从图片url中截取图片的精简的url（或者说是准确的url）
     * 
     * @param picUrl
     *            图片url
     * @return String 裁剪后的图片url
     */
    public static String subPicUrl(String picUrl) {

        /*
         * 1.之前是只裁剪第一个问号前面的内容，对只有一个问号的url有效
         * https://img-blog.csdnimg.cn/20190729002407657.png?x-oss-process=image/watermark
         * https://img-blog.csdnimg.cn/20190729002407657.png
         * 
         * 2. 如果url含有两个以上字符串则会失效，需要特殊处理
         * 先判断特殊情况：如果字符串包含https://ss.csdn.net/p?，（猜测是csdn的一种图床），直接取第一个问号和第二个问号之间的内容
         * https://ss.csdn.net/p?http://s1.51cto.com/images/20180513/1526178919312040.jpg?x-oss-process=image
         * 网址：http://s1.51cto.com/images/20180513/1526178919312040.jpg
         * 
         * https://ss.csdn.net/p?https://pic1.zhimg.com/80/v2-736e6985411c0823a43d326778ad74a2_hd.jpg
         * 网址：https://pic1.zhimg.com/80/v2-736e6985411c0823a43d326778ad74a2_hd.jpg
         */
        // 判断字符串中是否包含'?'
        int index1 = picUrl.lastIndexOf('?');
        if (index1 != -1) {
            // 如果包含，先判断是否是特殊情况
            // 定义csdn图床
            String csdnImg = "ss.csdn.net";
            if (picUrl.contains(csdnImg)) {
                String[] strings = picUrl.split("\\?", 5);
                picUrl = strings[1];
            } else {
                // 如果不是特殊情况，直接取第一个'?'前面的字符串
                picUrl = picUrl.substring(0, index1);
            }
        }

        return picUrl;
    }

    /**
     * 替换文件夹字符串中特殊字符（\ / : * ? " < > | 英文的这些字符），使其能成功创建文件夹
     * 
     * @param dirPath
     *            文件夹字符串
     * @return String 替换后的文件夹字符串
     */
    public static String replaceSpecialCharacters(String dirPath) {

        /*
         * windows下文件名中不能含有：\ / : * ? " < > | 英文的这些字符 ，这里使用"."、"'"进行替换。
         * \/:?| 用.替换
         * "<> 用'替换
         */
        dirPath = dirPath.replaceAll("[/\\\\:*?|]", ".");
        dirPath = dirPath.replaceAll("[\"<>]", "'");

        return dirPath;
    }

    /**
     * @Title checkPicName
     * @author yansheng
     * @version v1.0
     * @date 2019-08-11 15:08:41
     * @Description 验证图片名是否含有后缀，如https://blog.csdn.net/weixin_41287260/article/details/84403984
     *              博客的图片就是没有后缀的，，如果文件名太长（>17）,裁剪后面的17个字符；如果没有后缀，加上".jpg"后缀。
     *              <font style="color: red;"><b>注意：</b>关于“加上".jpg"后缀”这个行为，这里可能会出现一个误区：这里只是简单地将图片进行重命名，
     *              并没有改变图片的性质。就像你将一张图片GIF图片改后缀为jpg一样，它还是动图，而不是静态图。 这里添加后缀仅仅是为了能够让你的图片浏览器识别出来这是图片，不然没有后缀就识别不出来。</font>
     * @param picName
     *            图片文件名
     * @return String 处理后的图片文件名
     */
    public static String checkPicName(String picName) {
        // 定义后缀
        final String suffix = ".jpg";

        /*
         * 1. 通过观察图片文件名，发现：文件名中的'.'只出现在后缀中，这可能是图床的某种规则。
         * 所以我们可以通过判断字符串中是否含有'.'来判断该字符串是否含有扩展名，或者用其常见扩展名进行比较、判断。
         * 图片的常见扩展名：.jpg,.jpeg,.png,.gif
         * 
         * 2. 同时没有扩展名的图片名，可能很长，
         * 就像这个：https://imgconvert.csdnimg.cn/aHR0cHM6Ly9zMi5heDF4LmNvbS8yMDE5LzA3LzIwL1p6Q3ZFbi5qcGc，
         * 这里进行裁剪，取其后面的17位，就想这样：aHR0cHM6Ly9zMi5he，20190720 140659 433.png（猜测是图片上传时间，精确到毫秒）
         * 
         */
        // 如果文件名不包含扩展名，为其添加扩展名
        String dotString = ".";
        int picNameLength = 17;
        if (!picName.contains(dotString)) {
            int length = picName.length();
            if (length > picNameLength) {
                picName = picName.substring(length - picNameLength);
            }
            picName = picName + suffix;
        }

        return picName;
    }

}
