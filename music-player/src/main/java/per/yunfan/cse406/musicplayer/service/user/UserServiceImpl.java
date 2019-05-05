package per.yunfan.cse406.musicplayer.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.service.UserService;

import java.rmi.RemoteException;

/**
 * User service by RPC call
 */
public enum UserServiceImpl implements UserService {

    /**
     * Singleton implement by enum
     */
    INSTANCE;

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    /**
     * @return Service implement object(simple IoC design)
     */
    @Override
    public UserService getInstance() throws RemoteException {
        return INSTANCE;
    }

    /**
     * @return RMI service name string
     */
    @Override
    public String nameOfRMI() throws RemoteException {
        return "UserService";
    }

    /**
     * @return Service logger
     */
    @Override
    public Logger getLogger() throws RemoteException {
        return LOG;
    }
}
