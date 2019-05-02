# CSE406目前所有练习代码
## 注：这个工程采用Intellij idea，使用maven构建，采用JDK1.8
### 1. 项目说明
项目的依赖管理使用maven，详见pom.xml。
本项目分为四个模块：
1. java-ee Servlet相关知识课堂Demo
2. jdbc JDBC相关知识课堂Demo
3. jms JMS相关知识课堂Demo
4. rmi RMI相关知识课堂Demo

本工程的所有代码都在src/main下，其中：
* 源代码： src/main/java
* 资源文件： src/main/resources

***

### 2. JDBC文件说明
资源文件：
  * resources/create_procedure.sql ——JDBC课程中构建存储过程的SQL语句。
  * resources/test.sql ——学校服务器上"test"数据库建库的所有语句。
  * resources/per.yunfan.cse406.jdbc.properties ——JDBC配置文件，可以配置服务器、用户名、密码。

源码：
所有源码都在src/main/java/per/yunfan/cse406下
  * per.yunfan.cse406.jdbc/
    * JdbcUtils
    * statement/
      * ClassPractice
      * ClassPractice2
      * TestCallableStatement
      * TestPreparedStatement
      * TestStatement
  * jms/

***

### 4. RMI项目说明：
资源文件：空

源码：
所有源码都在src/main/java/per/yunfan/cse406/rmi下
 * per.yunfan.cse406.rmi/
   * Calc 表示RPC调用的接口定义
   * beans/ 表示RPC调用远程bean对象包
     * Group 一个Demo支持的封装3个int值的bean对象
   * client/ RPC客户端所在包
     * Client RPC客户端代码主类
   * server/ RPC服务端所在包
     * CalcImpl Calc接口的实际实现类
     * Server RPC服务端代码主类



2 列表
无序列表
* 项目1
* 项目2
* 项目3

有序列表
1. 项目1
2. 项目2
3. 项目3
   * 项目1
   * 项目2
3 其它


***
代码
`<hello world>`
代码块高亮
```ruby
  def add(a, b)
    return a + b
  end
  ```