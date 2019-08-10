# jsoup-crawl-csdn

用jsoup爬取csdn博客的一些信息。



## 主要内容

1. 获得博客数量
2. 获得博客列表页数
3. 获得博客列表信息（网址,创建时间,标题），如：
`Blog [url=https://blog.csdn.net/weixin_41287260/article/details/92185040, createTime=2019-06-15 20:45:43, title=阿里巴巴主导的“华山版《Java 开发手册》”简介]`



## 构建环境
jdk1.8+，maven3.0+，myeclipse2017

```maven
<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
		<dependency>
			<groupId>org.jsoup</groupId>
			<artifactId>jsoup</artifactId>
			<version>1.12.1</version>
		</dependency>
```





## 使用
1. git clone https://github.com/yansheng836/jsoup-crawl-csdn.git

2. 以maven形式导入(my)eclipse。

3. 运行`\jsoup-crawl-csdn\src\main\java\xyz\yansheng\jsoup_crawl_csdn`下面的测试程序。

（4 . 或者按需开发自己的程序，例如：获取文章信息、获取每篇博客的图片的信息……）

