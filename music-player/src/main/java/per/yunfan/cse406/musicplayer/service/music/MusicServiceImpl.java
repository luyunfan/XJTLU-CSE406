package per.yunfan.cse406.musicplayer.service.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.MusicDAO;
import per.yunfan.cse406.musicplayer.model.Music;
import per.yunfan.cse406.musicplayer.service.MusicService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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
     * Music DAO object
     */
    private final MusicDAO musicDAO = MusicDAO.instance();

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

    /**
     * Get all music from database
     *
     * @return All music list
     */
    @Override
    public List<Music> getAllMusic() {
        try {
            return musicDAO.getAllMusic();
        } catch (SQLException e) {
            LOG.error("Get All music failure! ", e);
            return Collections.emptyList();
        }
    }
}
