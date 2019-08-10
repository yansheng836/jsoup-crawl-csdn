# jsoup-crawl-csdn

用jsoup爬取csdn博客的一些信息。



## 初衷

逛CSDN时，总会发现有些博客因为年代比较久远，而造成博客图片的丢失，阅读博客的体验极差；因为嵌入的图片中可能包含一些和内容联系比较紧密的信息，图片丢失会使博客断断续续、不易理解。



于是我突然想将csdn博客中的图片下载下来，然后进行备份：保存到本地、上传百度云、上传图床等，然后这个项目就诞生了。



## 主要内容

1. 获得博客数量。

2. 获得博客列表页数。

3. 获得博客列表信息（网址,创建时间,标题），如：


`Blog [url=https://blog.csdn.net/weixin_41287260/article/details/92185040, createTime=2019-06-15 20:45:43, title=阿里巴巴主导的“华山版《Java 开发手册》”简介]`

4. 获得博客的图片链接。
5. 下载博客的所有图片，进行备份，防止博客中的图片丢失。



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



注：如果不使用maven框架进行构建，可以到https://mvnrepository.com/artifact/org.jsoup/jsoup下载jar包，然后加到类路径中。



## 使用
1. git clone https://github.com/yansheng836/jsoup-crawl-csdn.git

2. 以maven形式导入(my)eclipse。

3. 运行`\jsoup-crawl-csdn\src\main\java\xyz\yansheng\main`下面的测试程序。

   4 . 或者按需开发自己的程序，例如：获取文章信息……

