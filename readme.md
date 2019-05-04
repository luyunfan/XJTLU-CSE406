# CSE406目前所有练习代码
## 注：这个工程采用Intellij idea，使用maven构建，采用JDK1.8
### 1. 项目说明
项目的依赖管理使用maven，详见pom.xml。
本项目分为四个模块：
1. java-ee Servlet相关知识课堂Demo
2. jdbc JDBC相关知识课堂Demo
3. jms JMS相关知识课堂Demo
4. rmi RMI相关知识课堂Demo

本工程的所有代码都在src/main下；本工程的文件中：
* 源代码： src/main/java
* 资源文件： src/main/resources
* Maven依赖管理配置： pom.xml

***

### 2. Java-EE（Servlet）模块文件说明
资源文件：空

源码：
所有源码都在src/main/java/per/yunfan/cse406/java-ee下
  * per.yunfan.cse406.javaee/
    * add/ 包含一个加法demo的练习
        * AddServlet 对两个浮点数相加的Demo
    * beans/ 模块需要用到的所有Java Bean
        * User 封装了用户信息的Java Bean类
    * cookie/ 操作Cookie相关Demo
        * CookieReadServlet 读取已经保存的Cookie
        * CookieServlet 写入Cookie
    * first/ 第一次课堂练习
        * LoginServlet 一个简单的登录Demo
    * hello/ Hello World程序所在包
        * HelloServlet Hello World Servlet
    * session/ 操作Session相关Demo
        * SessionReadServlet 读取已经保存的Session
        * SessionServlet 写入Session
    
WEB文件夹：
    * web/ Servlet项目的标准web文件夹
        * WEB-INF/ 浏览器不能访问的WEB-INF文件夹
            * web.xml Servlet的配置文件
        * add.html 计算加法输入的网页
        * add.jsp 使用jsp显示计算加法的结果
        * failure.html 登录失败跳转的网页
        * index.jsp 第一个jsp Demo
        * login.html 登录Demo对应的页面
        * loginSession.html 测试Session Servlet所使用的登录页面
        * success.jsp 登陆Demo登录成功的jsp页面

pom.xml包含的依赖：
1. servlet 4.0.1 API

本WEB项目使用tomcat 9.0.17测试通过

***

### 3. JDBC模块文件说明
资源文件：
  * resources/create_procedure.sql ——JDBC课程中构建存储过程的SQL语句。
  * resources/test.sql ——学校服务器上"test"数据库建库的所有语句。
  * resources/jdbc.properties ——JDBC配置文件，可以配置服务器、用户名、密码。

源码：
所有源码都在src/main/java/per/yunfan/cse406下
  * per.yunfan.cse406.jdbc/
    * JdbcUtils 读取JDBC配置信息并提供SQL执行辅助的工具类
    * statement/ 所有JDBC的Demo和课堂练习
      * ClassPractice 课堂练习代码
      * ClassPractice2 课堂练习代码2
      * TestCallableStatement JDBC调用存储过程的Demo
      * TestPreparedStatement 使用PreparedStatement对象的Demo
      * TestStatement 使用Statement对象的Demo

pom.xml包含的依赖：
1. MySQL connector 8.0.15

***

### 4. JMS模块文件说明
资源文件：空

源码：
所有源码都在src/main/java/per/yunfan/cse406/jms下
 * per.yunfan.cse406.jms/
    * JMSConfig 消息队列配置的工具类，提供连接ActiveMQ的功能
    * calculation/ 一个使用JMS实现RPC调用的计算器Demo
        * Client 发起计算的客户端主类
        * Expression 封装了计算表达式的Java Bean
        * Operator 一个包含加减乘除的运算符枚举
        * Server 接收并完成计算的服务端主类
    * logger/ 一个使用JMS传输日志的Demo
        * Client001 发起日志的1号客户端
        * Client002 发起日志的2号客户端
        * Event 封装了日志信息的Java Bean
        * Logger 发送日志逻辑的工具类
        * Server 接收日志的服务端
    * object/ 使用JMS传输对象的Demo
        * ObjectConsumer 传输对象的消费者主类
        * ObjectProducer 传输对象的生产者主类
        * PaymentEvent 用来传输的Java Bean对象类
    * p2p/ 使用JMS的点对点模型Demo
        * Consumer 点对点模型的消费者主类
        * Producer 点对点模型的生产者主类
    * topic/ 使用JMS的发布订阅模型Demo
        * TopicConsumer 发布订阅模型的消费者主类
        * TopicProducer 发布订阅模型的生产者主类
    
pom.xml包含的依赖：
1. ActiveMQ client 5.15.8

***

### 5. RMI模块说明：
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

pom.xml包含的依赖：无