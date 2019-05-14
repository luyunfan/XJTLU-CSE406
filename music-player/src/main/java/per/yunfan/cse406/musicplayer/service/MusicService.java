package per.yunfan.cse406.musicplayer.service;

import per.yunfan.cse406.musicplayer.model.po.Comment;
import per.yunfan.cse406.musicplayer.model.po.Music;
import per.yunfan.cse406.musicplayer.service.music.MusicServiceImpl;
import per.yunfan.cse406.musicplayer.utils.Optional;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

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
    List<Music> getAllMusic() throws RemoteException;

    /**
     * Get all music basically information from database
     *
     * @return (Music id, Music name)
     */
    Map<Integer, String> getAllMusicInformation() throws RemoteException;

    /**
     * Get a music information by music play id
     *
     * @param id Music id
     * @return Music object
     */
    Optional<Music> getMusicById(int id) throws RemoteException;

    /**
     * Create a new comment
     *
     * @param comment Comment object
     * @return Is successful
     */
    boolean createComment(Comment comment) throws RemoteException;
}
