package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.service.user.UserServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public interface UserService extends Remote, RMIService<UserService> {
    static UserService instance() throws RemoteException {
        return UserServiceImpl.INSTANCE;
    }

    @Override
    default UserService getClient(String host, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        return (UserService) registry.lookup(this.nameOfRMI());
    }
}
