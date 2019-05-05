package per.yunfan.cse406.musicplayer.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.UserDAO;

public enum UserDAOImpl implements UserDAO {
    INSTANCE;


    /**
     * Logger object by log4j2
     */
    private final Logger LOG = LogManager.getLogger(UserDAOImpl.class);
}
