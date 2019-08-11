/**
 * @Title StringUtilTest.java
 * @Package xyz.yansheng.utility
 * @Description TODO
 * @author yansheng
 * @date 2019-08-11 14:28:59
 * @version v1.0
 */
package xyz.yansheng.utility;

import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-11 14:28:59
 * @version v1.0 
 */
public class StringUtilTest {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-11 14:28:59
	 * @Description 测试StringUtil的方法
	 */
	public static void main(String[] args) {

		// 1.测试方法:StringUtil.subPicUrl(picUrl)
		//subPicUrlTest();

		// 2.测试方法:StringUtil.replaceSpecialCharacters(dirPath)
		//replaceSpecialCharactersTest();
		
		// 3.测试方法:StringUtil.checkPicName(picName)
		checkPicNameTest();

	}

	public static void subPicUrlTest() {
		// 含有1个'?'的字符串
		String picUrl = "https://img-blog.csdn.net/20171026155003532?"
				+ "watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamF2YV9tZHp5/font/5a6L5L2T/"
				+ "fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast";

		// 特殊情况，含有ss.csdn.net，并含有2个'?'的字符串
		String picUrl1 = "https://ss.csdn.net/p?http://s1.51cto.com/images/20180513"
				+ "/1526178919312040.jpg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,"
				+ "color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=";

		picUrl = StringUtil.subPicUrl(picUrl);
		System.out.println(picUrl);

		picUrl1 = StringUtil.subPicUrl(picUrl1);
		System.out.println(picUrl1);
	}

	public static void replaceSpecialCharactersTest() {

		String dirPath = "2019-05-26-java.lang.NoClassDefFoundError: org/junit/platform/launcher/core/LauncherFactory";

		//windows下文件名中不能含有：\ / : * ? " < > | 英文的这些字符 
		dirPath = StringUtil.replaceSpecialCharacters(dirPath);

		System.out.println(dirPath);
	}

	public static void checkPicNameTest() {

		String[] picNames = {
				"aHR0cDovL2ltZy5tcC5pdGMuY24vdXBsb2FkLzIwMTcwNjE3LzZhMjU2MTRhM2NiODRjODFhZGMyMDI2MzgxOGU5MDkwLmpwZw",
				"aHR0cDovL2ltZy5tcC5pdGMuY24vdXBsb2FkLzIwMTcwNjE3LzYwNTY2ZjFlMzIwOTQ0MTc4ZmRhNWRmZTY5MDg0YWJiX3RoLmpwZw",
				"20160806212058689"};
		for (String picName : picNames) {
			System.out.println(StringUtil.checkPicName(picName));
		}
	}

}
