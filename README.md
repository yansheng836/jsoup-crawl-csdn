# jsoup-crawl-csdn

用jsoup爬取csdn博客的一些信息。


## 初衷

逛CSDN时，总会发现有些博客因为年代比较久远，而造成博客图片的丢失，阅读博客的体验极差；因为嵌入的图片中可能包含一些和内容联系比较紧密的信息，图片丢失会使博客断断续续、不易理解。

于是我突然想将csdn博客中的图片下载下来，然后进行备份：保存到本地、上传百度云、上传图床等，然后这个项目就诞生了。


## 主要内容

1. 获得博客数量。

2. 获得博客列表页数。

3. 获得博客列表信息（网址,创建时间,标题），如：
```
Blog [url=https://blog.csdn.net/weixin_41287260/article/details/92185040, createTime=2019-06-15 20:45:43, title=阿里巴巴主导的“华山版《Java 开发手册》”简介]
```
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

注：如果不使用maven框架进行构建，可以到 <https://mvnrepository.com/artifact/org.jsoup/jsoup> 下载jar包，然后加到类路径中。



## 使用
1. git clone https://github.com/yansheng836/jsoup-crawl-csdn.git

2. 以maven形式导入(my)eclipse。

3. 运行`\jsoup-crawl-csdn\src\main\java\xyz\yansheng\main`下面的测试程序。

4. 或者按需开发自己的程序，例如：获取文章信息……



## 待修复bug

1. 当标题中含有`/`时，创建文件夹会失败，比如：`2019-05-26-java.lang.NoClassDefFoundError: org/junit/platform/launcher/core/LauncherFactory`

同时，如果穿件文件夹失败，应该continue，不再继续下载图片，因为路径错误，会没有意义！

2. 创建`2019-07-25-git 错误 fatal: Not a valid object name: 'master'.//失败`
3. 2019-05-04-git报错“fatal: refusing to merge unrelated histories”处理方案//失败
4. 2019-01-06-手把手教你如何安装Pycharm——靠谱的Pycharm安装详细教程//成功

 --下载图片:https://ss.csdn.net/p 成功！保存位置为：E://2CSDN//2019-01-06-手把手教你如何安装Pycharm——靠谱的Pycharm安装详细教程//p
 --下载图片:https://ss.csdn.net/p 成功！保存位置为：E://2CSDN//2019-01-06-手把手教你如何安装Pycharm——靠谱的Pycharm安装详细教程//p
 --下载图片:https://ss.csdn.net/p 成功！保存位置为：E://2CSDN//2019-01-06-手把手教你如何安装Pycharm——靠谱的Pycharm安装详细教程//p

<https://ss.csdn.net/p?http://s1.51cto.com/images/20180513/1526178919312040.jpg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=>
尝试：当超链接有两个问号时取第二个分号内容


5. 第88篇博客-图片创建文件夹：E://2CSDN//2018-12-01-解决Eclipse和myeclipse在进行 html,jsp等 页面编辑时，自动格式化变丑的问题//成功
图片链接(http://images.cnitblog.com/blog/534652/201501/111641302036764.png)无效！

6. 第53篇博客-图片	at java.io.FileOutputStream.open0(Native Method)
   	at java.io.FileOutputStream.open(FileOutputStream.java:270)
   	at java.io.FileOutputStream.<init>(FileOutputStream.java:213)
   	at java.io.FileOutputStream.<init>(FileOutputStream.java:162)
   	at xyz.yansheng.utility.FileUtil.downloadPic(FileUtil.java:108)
   	at xyz.yansheng.main.DownloadBlogsPictures.main(DownloadBlogsPictures.java:82)
   文件夹：E://2CSDN//2019-04-06-myeclipse版本简单介绍//已存在