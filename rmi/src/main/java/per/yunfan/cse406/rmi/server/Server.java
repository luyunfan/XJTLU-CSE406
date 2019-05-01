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
        Registry registry = LocateRegistry.createRegistry(2019);
        CalcImpl impl = new CalcImpl();

        Remote remote = UnicastRemoteObject.exportObject(impl, 2019);

        registry.rebind("CALC", remote);
        System.out.println("Server start");
    }
}
