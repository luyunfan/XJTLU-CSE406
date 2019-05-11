package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.enums.UserStates;
import per.yunfan.cse406.musicplayer.model.po.User;
import per.yunfan.cse406.musicplayer.service.user.UserServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;

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

    /**
     * Login user method
     *
     * @param username Input user name
     * @param password Input password
     * @return if login success, return an User object
     */
    Optional<User> login(String username, String password) throws RemoteException;

    /**
     * Sign in a new user
     *
     * @param username username
     * @param password password
     * @return Sign in States
     */
    UserStates signIn(String username, String password) throws RemoteException;
}
