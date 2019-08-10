/**
 * @Title Blog.java
 * @Package xyz.yansheng.entity
 * @Description TODO
 * @author yansheng
 * @date 2019-08-10 18:34:35
 * @version v1.0
 */
package xyz.yansheng.entity;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author yansheng
 * @date 2019-08-10 18:34:35
 * @version v1.0 
 */
public class Blog {

	private String url;
	private String createTime;
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Blog [url=" + url + ", createTime=" + createTime + ", title=" + title + "]";
	}

}
