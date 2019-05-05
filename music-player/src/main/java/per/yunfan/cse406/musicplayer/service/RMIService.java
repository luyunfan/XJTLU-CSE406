package per.yunfan.cse406.musicplayer.service;

import org.apache.logging.log4j.Logger;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI Service base interface
 *
 * @param <T> Specifically type of RMI service
 */
public interface RMIService<T extends Remote> {

    /**
     * @return Service implement object(simple IoC design)
     */
    T getInstance() throws RemoteException;

    /**
     * @return RMI service name string
     */
    String nameOfRMI() throws RemoteException;

    /**
     * @return Service logger
     */
    Logger getLogger() throws RemoteException;

    /**
     * get RMI client object
     *
     * @param host host
     * @param port port
     * @return Client proxy object
     */
    T getClient(String host, int port) throws RemoteException, NotBoundException;

    /**
     * Start RMI server
     *
     * @param port port
     */
    default void startServer(int port) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);
        Remote remote = UnicastRemoteObject.exportObject(this.getInstance(), port);
        registry.rebind(this.nameOfRMI(), remote);
        this.getLogger()
                .info("RMIServer start: " + nameOfRMI());
    }
}
