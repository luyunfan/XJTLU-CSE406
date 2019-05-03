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

### 2. Java-EE模块文件说明

***

### 3. JDBC模块文件说明
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