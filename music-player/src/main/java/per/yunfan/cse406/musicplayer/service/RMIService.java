package per.yunfan.cse406.musicplayer.service;

import org.apache.logging.log4j.Logger;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public interface RMIService<T extends Remote> {

    T getInstance() throws RemoteException;

    String nameOfRMI() throws RemoteException;

    Logger getLogger() throws RemoteException;

    T getClient(String host, int port) throws RemoteException, NotBoundException;

    default void startServer(int port) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);
        Remote remote = UnicastRemoteObject.exportObject(this.getInstance(), port);
        registry.rebind(this.nameOfRMI(), remote);
        this.getLogger().info("RMIServer start: " + nameOfRMI());
    }
}
