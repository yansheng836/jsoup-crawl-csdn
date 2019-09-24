package xyz.yansheng.entity;

/**
 * 博客实体类
 * @author yansheng
 * @date 2019/08/10
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
