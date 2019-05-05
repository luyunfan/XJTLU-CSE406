package per.yunfan.cse406.musicplayer.dao.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.MusicDAO;

/**
 * MusicDAO implement class
 */
public enum MusicDAOImpl implements MusicDAO {

    /**
     * Singleton implement by enum
     */
    INSTANCE;

    /**
     * Logger object by log4j2
     */
    private final Logger LOG = LogManager.getLogger(MusicDAOImpl.class);
}
