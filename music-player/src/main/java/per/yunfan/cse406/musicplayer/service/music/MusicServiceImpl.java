package per.yunfan.cse406.musicplayer.service.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.service.MusicService;

import java.rmi.RemoteException;

/**
 * Music service by RPC call
 */
public enum MusicServiceImpl implements MusicService {

    /**
     * Singleton implement by enum
     */
    INSTANCE;

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(MusicServiceImpl.class);

    /**
     * @return Service implement object(simple IoC design)
     */
    @Override
    public MusicService getInstance() throws RemoteException {
        return INSTANCE;
    }

    /**
     * @return RMI service name string
     */
    @Override
    public String nameOfRMI() throws RemoteException {
        return "MusicService";
    }

    /**
     * @return Service logger
     */
    @Override
    public Logger getLogger() throws RemoteException {
        return LOG;
    }
}
