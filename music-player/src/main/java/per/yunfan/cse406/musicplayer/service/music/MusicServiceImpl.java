package per.yunfan.cse406.musicplayer.service.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.service.MusicService;

import java.rmi.RemoteException;

/**
 * Music service by RPC call
 */
public enum MusicServiceImpl implements MusicService {
    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(MusicServiceImpl.class);

    @Override
    public MusicService getInstance() throws RemoteException {
        return INSTANCE;
    }

    @Override
    public String nameOfRMI() throws RemoteException {
        return "MusicService";
    }

    @Override
    public Logger getLogger() throws RemoteException {
        return LOG;
    }
}
