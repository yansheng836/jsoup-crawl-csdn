/**
 * @Title TestMkdir.java
 * @Package xyz.yansheng.main
 * @Description TODO
 * @author yansheng
 * @date 2019-08-11 03:41:27
 * @version v1.0
 */
package xyz.yansheng.main;

import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-11 03:41:27
 * @version v1.0 
 */
public class TestMkdir {

	/**
	 * @Title main
	 * @author yansheng
	 * @version v1.0
	 * @date 2019-08-11 03:41:27
	 * @Description TODO
	 * @param args   
	 * void 
	 * @see  
	 * @exception
	 */
	public static void main(String[] args) {
		
		String dirPath = "2019-05-26-java.lang.NoClassDefFoundError: org/junit/platform/launcher/core/LauncherFactory";
		String afterChangeDirPath = null;

		//windows下文件名中不能含有：\ / : * ? " < > | 英文的这些字符 

		afterChangeDirPath = dirPath.replaceAll("[/\\\\:*?<>|]", ".");

		System.out.println(afterChangeDirPath);
		
		File file = new File(afterChangeDirPath);
		file.mkdir();
	}

	
}
