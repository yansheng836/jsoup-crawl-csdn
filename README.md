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






## 已修复bug

1. 如果穿件文件夹失败，直接continue，不再继续下载图片，因为保存路径错误，不可能保存成功，故继续执行没有意义！

2. 替换文件名字符串中的特殊字符，使其能够成功创建文件夹：

`windows下文件名中不能含有：\ / : * ? " < > | 英文的这些字符 ，这里使用"."、"'"进行替换。`

3.  对图片的超链接进行裁剪，之前是裁剪第一个问号前面的字符串subString（0，第一个问号），像这样：https://img-blog.csdnimg.cn/20190729002407657.png?x-oss-process=image/watermark；后面发现有些网址有两个问号，就像这样：https://ss.csdn.net/p?http://s1.51cto.com/images/20180513/1526178919312040.jpg?x-oss-process=image/watermark，现在改成裁剪最后一个问号前面的网址，需要裁剪两次：subString（0，最后一个问号），subString（最后一个问号，字符串长度）；

4. 对于图片名太长的字符串进行裁剪，保留其后面的17位（不包含后缀名）；对没有后缀名的图片，为其添加".jpg"后缀，注意这里并没有改变图片格式，仅仅是简单的重命名。打个比方：如果原图片是动图，你修改文件名为jpg后，它还是动图。