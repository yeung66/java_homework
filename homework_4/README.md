实验要求 



学习：



1. 面向接口编程的概念和原理

2. 爬虫基本概念，使用的技术手段

3. Jsoup的基本用法

4. （选学）Java多线程相关知识



作业：



1.    根据 onedrive共享文档“选课列表.xls” （https://1drv.ms/x/s!ArbKS2ZwaIiPmh-m60F3i-5YO2Cz ）从上到下，每临近的两个同学分配一个大学网站进行后续操作。大学列表依据US.NEWS 2018 世界排名：https://www.usnews.com/education/best-global-universities/rankings 然后找到自己partner以及要爬虫的学校后，补充”选课列表.xls”（可以直接在线编辑）。填表截至日期：12.6晚10：00. （具体的在线界面如下图所示）

 image.png

2.    找到自己要进行爬虫的学校后，找到该学校对应的graduate school (相当于我们的研究生院，所有的研究生项目都在这个网站)。推荐快速寻找该学校graduate school的方法为，在浏览器中输入  学校名称graduate school，如：Stanford graduate school，然后一般第一个就是：如

 image.png



3. 找到该校的研究生院后进入该网址，依然以斯坦福大学为例。进入后找到所有的program list的列表所在的网址，如：https://gradadmissions.stanford.edu/programs 。 然后在爬取该界面的信息，提取到所有program的名字和对应详情的URL。

4. 遍历刚才得到的项目列表和对应的URL，在研究生院下各个项目的详情界面，如斯坦福大学的计算机科学项目：https://gradadmissions.stanford.edu/programs/computer-science 抓取以下信息：

① Program Name, 项目名称：如Computer Science

② School, 开设此项目的学院的名称，如: School of Engineering

③ homepage, 具体开设该项目的主页URL，如: http://www.cs.stanford.edu/

④ degree, 开设的学位，如：MS，PhD. 

⑤ Deadline with Aid，有奖学金的申请截止日期，如December 5, 2017

⑥ Deadline without Aid，无奖学金的申请截止日期，如December 5, 2017

⑦ email, 申请项目遇到问题反馈的邮箱，如admissions@cs.stanford.edu

⑧ location，开设项目所在地址，如Computer Science Graduate Admissions, Gates Building, Room 196, 353 Serra Mall，Stanford, CA 94305-9015

⑨ phone, 项目申请联系电话，如1 (650) 725-3140

注意：并不是每个学校的项目详情下都有这些信息，但是应该主要的大部分信息都会有的，如果某些学校没有某项信息，请在最后结果汇总时输出NULL。



5. 将4中爬取的信息连同学校信息和学校所在国家信息（这个可以写死，不用爬虫，但请与USNEWS官方说法保持一致，如在刚才查看ranking的USNEWS 官网上对Harvard Univ. 国家和地区信息为： United States）一起写入txt文件中。文件名字直接以该学校的名字命名。字段顺序为：

学校名称，所在国家和地区，项目名称，开设学院，学位，邮箱，电话，地址，有奖ddl, 无奖ddl, 项目主页地址。

注意，不用写入表头信息，只写入具体信息，每个字段中间用”\t”隔开。此外，如果一个项目下开设有多个degree(学位)对应有多个截至日期，请分成两条写入文件。如：Stanford Computer Science开设phd和ms两种学位，分别对应有两个日期，书写是分为两条写入:

Stanford University United States Computer Science School of Engineering MS …….

Stanford University United States Computer Science School of Engineering PhD …….

另外官网上空缺的信息一律用字符串“NULL“替换。



提示: 本次作业基于面向接口编程，请事先学习面向接口编程的相关概念，然后开始本实验。在本实验所给的事例结构中，请创建自己的类，实现FileHandler, Parser, WebSpider接口，并在Manager类中使用静态块将它们注册到SearchManager中，禁止修改search.impl包外任何代码结构，禁止修改接口结构。

WebSpider用于爬取信息，Parser用于解析信息，FileHandler用于输出到文件。



加分内容（选做）：



爬虫时使用多线程。


