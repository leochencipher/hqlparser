##A parser for hive sql
###ANTLRNoCaseFileStream
this java file is a fix for the default case-sensitive parser

###Test.java
this Test-drive dependent on four regular antlr jars
1.hiveParser其实是在hive api里面的，位置在：
org.apache.hadoop.hive.ql.parse.HiveParser

2.关于Hive SQL解析/执行计划生成流程分析：
http://blog.csdn.net/wf1982/article/details/9122543

3.我们开发时只要import org.apache.hadoop.hive.ql.parse.*;就导入了词法语法校验的功能了。

4.所以就不存在hive更新要重新编译hiveparser，hivelexer的问题了。

5.无需关心hive.g语法文件的编写。

几个问题补充：
1.调用hiveparser和hivelexer是要用到antlr库
所以以下四个依赖是必须的()：
    antlr-2.7.7.jar
	antlr-3.0.1.jar
	antlr-runtime-3.0.1.jar
	stringtemplate-3.1b1.jar
2.由于hive.g默认对关键字是大小写敏感的，所以默认情况下：
select a from b;
是错误的,
应该改成;SELECT a FROM b;
所以在调用parser时要编写一个过滤流：
public class ANTLRNoCaseFileStream extends ANTLRFileStream {
    public ANTLRNoCaseFileStream(String fileName) throws IOException {

        super(fileName, null);

    }

    public ANTLRNoCaseFileStream(String fileName, String encoding)

    throws IOException {

        super(fileName, encoding);

    }

    public int LA(int i) {

        if (i == 0) {

            return 0; // undefined

        }

        if (i < 0) {

            i++; // e.g., translate LA(-1) to use offset 0

        }

        if ((p + i - 1) >= n) {

            return CharStream.EOF;

        }

        return Character.toUpperCase(data[p + i - 1]);

    }
}


	


