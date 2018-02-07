实验要求
现有股票数据XX条，无序存储在txt文本文件中。要求使用JAVA I.O数据流对数据进行读取并对相关数据进行排序操作后再重新写入新的TXT文件中。具体要求如下：

1. 建立实体类StockInfo, 用于存储读入的数据。

2. 实现接口FileHandler。FileHandler负责进行文件的读取。

3. 实现接口StockSorter。StockSorter主要用于对读取的数据进行排序。排序的具体要求为：根据每条记录的answer的长度，按从大到小（降序descending）或从小到大（升序ascending）排序。如不加约束，默认为升序。

注意：实现排序算法可以使用冒泡排序，插入排序，堆排序，选择排序，快速排序等，任何的排序算法都是可以被接受的，但不能使用系统库中自带的排序函数，如List.sort() 或者 Arrays.sort()。

4． 运行Main.java对前三步的操作进行检查。若均提示成功，证明本次实验成功，有一项失败即为不合格。

提交作业方式：见课程：Assignment Submission

提交截止日期：加分due：10.22，22：00,普通due：10.29，22：00

附：所给数据格式描述

ID,评论id

TITLE,文章标题

AUTHOR,文章作者

DATE,发布时间

LASTUPDATE,最新修改时间

CONTENT,文章内容

ANSWERAUTHOR,评论者

ANSWER,评论内容

输入描述：

所给数据中，每条数据占一行，每行使用\n进行换行，数据之间使用\t作为分隔符

输出要求：

根据所给数据，按照评论内容的长度排序，并将排序后的数据按照顺序输出，要求每条数据占一行，每行使用\n进行换行，数据之间使用\t作为分隔符