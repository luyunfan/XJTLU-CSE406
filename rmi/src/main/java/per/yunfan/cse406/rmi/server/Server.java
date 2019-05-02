package per.yunfan.cse406.rmi.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI服务端开启的主类
 */
public final class Server {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(2019);  //生成注册中心，监听2019端口
        CalcImpl impl = new CalcImpl();  //实际业务逻辑的实现类

        Remote remote = UnicastRemoteObject.exportObject(impl, 2019);  //将实现类实现代理

        registry.rebind("CALC", remote);  //绑定代理对象
        System.out.println("Server start");
    }
}
