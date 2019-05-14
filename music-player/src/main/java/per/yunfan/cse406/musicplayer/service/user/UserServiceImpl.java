package per.yunfan.cse406.musicplayer.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.UserDAO;
import per.yunfan.cse406.musicplayer.enums.UserStates;
import per.yunfan.cse406.musicplayer.model.po.User;
import per.yunfan.cse406.musicplayer.model.vo.UserInfoVO;
import per.yunfan.cse406.musicplayer.service.UserService;
import per.yunfan.cse406.musicplayer.utils.Optional;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;

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
     * UserDAO object
     */
    private final UserDAO userDAO = UserDAO.instance();

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

    /**
     * Login user method
     *
     * @param username Input user name
     * @param password Input password
     * @return if login success, return an User object
     */
    @Override
    public Optional<User> login(String username, String password) {
        try {
            return userDAO.login(username, password);
        } catch (SQLException e) {
            LOG.error("Login failure in DAO object", e);
            return Optional.empty();
        }
    }

    /**
     * Sign in a new user
     *
     * @param username username
     * @param password password
     * @return Sign in States
     */
    @Override
    public UserStates signIn(String username, String password) {
        try {
            return userDAO.signIn(username, password);
        } catch (SQLException e) {
            LOG.error("Sign in user: " + username + " failure in DAO object", e);
            return UserStates.UNKNOWN_ERROR;
        }
    }

    /**
     * Modify the user's information
     *
     * @param username     Username
     * @param gender       User's gender
     * @param birthday     User's Birthday
     * @param introduction User's introduction
     * @return Is successful
     */
    @Override
    public boolean modifyUserInfo(String username, char gender, LocalDate birthday, String introduction) throws RemoteException {
        try {
            return userDAO.modifyUserInfo(username, gender, birthday, introduction);
        } catch (SQLException e) {
            LOG.error("Modify User: " + username + "'s information failure in DAO object", e);
            return false;
        }
    }

    /**
     * Get user information by username
     *
     * @param userName Username
     * @return User's information if this user is exist
     */
    @Override
    public Optional<UserInfoVO> getUserInfoByName(String userName) throws RemoteException {
        try {
            return userDAO.getUserInfoByName(userName);
        } catch (SQLException e) {
            LOG.error("Finding user: " + userName + " failure!", e);
            return Optional.empty();
        }
    }
}
