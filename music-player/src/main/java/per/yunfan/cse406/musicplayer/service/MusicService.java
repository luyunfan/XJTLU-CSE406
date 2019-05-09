package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.service.music.MusicServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Music service interface
 */
public interface MusicService extends Remote, RMIService<MusicService> {

    /**
     * A simple IoC design, it will return the MusicServiceImpl object
     *
     * @return object of MusicService implement class
     */
    static MusicService instance() throws RemoteException {
        return MusicServiceImpl.INSTANCE;
    }

    /**
     * @return RMI server port
     */
    static int port() throws RemoteException {
        return 50071;
    }

    /**
     * get RMI client object
     *
     * @param host host
     * @param port port
     * @return Client proxy object
     */
    @Override
    default MusicService getClient(String host, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        return (MusicService) registry.lookup(this.nameOfRMI());
    }

    /**
     * Get all music from database
     *
     * @return All music list
     */
    List getAllMusic();
}
