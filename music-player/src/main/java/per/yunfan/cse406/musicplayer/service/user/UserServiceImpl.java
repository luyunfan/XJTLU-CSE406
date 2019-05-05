package per.yunfan.cse406.musicplayer.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.service.UserService;

import java.rmi.RemoteException;

/**
 * User service by RPC call
 */
public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserService getInstance() throws RemoteException {
        return INSTANCE;
    }

    @Override
    public String nameOfRMI() throws RemoteException {
        return "UserService";
    }

    @Override
    public Logger getLogger() throws RemoteException {
        return LOG;
    }
}
