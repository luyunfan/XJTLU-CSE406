package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.service.user.UserServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * User service interface
 */
public interface UserService extends Remote, RMIService<UserService> {

    /**
     * A simple IoC design, it will return the UserServiceImpl object
     *
     * @return object of UserService implement class
     */
    static UserService instance() throws RemoteException {
        return UserServiceImpl.INSTANCE;
    }

    /**
     * @return RMI server port
     */
    static int port() throws RemoteException {
        return 50072;
    }

    /**
     * get RMI client object
     *
     * @param host host
     * @param port port
     * @return Client proxy object
     */
    @Override
    default UserService getClient(String host, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        return (UserService) registry.lookup(this.nameOfRMI());
    }
}
