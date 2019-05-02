package per.yunfan.cse406.rmi.client;

import per.yunfan.cse406.rmi.Calc;
import per.yunfan.cse406.rmi.beans.Group;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI客户端进行请求程序的主类
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 2019); //寻找注册中心（地址，端口）
        Calc bind = (Calc) registry.lookup("CALC");  //获取代理对象


        System.out.println(bind.add(1, 1));  //执行RPC调用

        System.out.println(bind.compare(1, 2));
        System.out.println(bind.compare(1, 1));
        System.out.println(bind.compare(2, 1));

        Group groupA = new Group(1, 2, 3);
        Group groupB = new Group(2, 3, 4);
        Group groupC = new Group(1, 2, 3);

        System.out.println(bind.equal(groupA, groupB));  //测试Java Bean作为参数的调用
        System.out.println(bind.equal(groupA, groupC));
    }
}
