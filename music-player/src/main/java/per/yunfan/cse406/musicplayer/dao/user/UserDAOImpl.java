package per.yunfan.cse406.musicplayer.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.UserDAO;
import per.yunfan.cse406.musicplayer.enums.UserStates;
import per.yunfan.cse406.musicplayer.model.User;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;
import per.yunfan.cse406.musicplayer.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * UserDAO implement class
 */
public enum UserDAOImpl implements UserDAO {

    /**
     * Singleton implement by enum
     */
    INSTANCE;


    /**
     * Logger object by log4j2
     */
    private final Logger LOG = LogManager.getLogger(UserDAOImpl.class);

    /**
     * Login user method
     *
     * @param userName Input user name
     * @param password Input password
     * @return if login success, return an User object
     * @throws SQLException SQL query exception
     */
    @Override
    public Optional<User> login(String userName, String password) throws SQLException {
        if (userName == null || password == null) {
            return Optional.empty();
        }
        Optional<User> result = findUserByName(userName);
        if (result.isPresent()) {
            User user = result.get();
            if (user.getPassword() == null || !user.getPassword().equals(PasswordUtils.encryptedByMD5(password))) {
                return Optional.empty();
            }
        }
        return result;
    }

    /**
     * Sign in a new user
     *
     * @param userName username
     * @param password password
     * @return Sign in States
     * @throws SQLException SQL query exception
     */
    @Override
    public UserStates signIn(String userName, String password) throws SQLException {
        if (userName == null ||
                userName.isEmpty() ||
                password == null ||
                password.isEmpty()) {
            return UserStates.USERNAME_ILLEGAL;
        }
        Optional<User> found = findUserByName(userName);
        if (found.isPresent()) { //Already exists
            return UserStates.ALREADY_EXIST;
        }
        String sql = "INSERT INTO user(username, password) VALUES(?, ?);";
        Connection connection = JDBCUtils.getConnection();
        int line = JDBCUtils
                .executeUpdate(connection, sql, userName, PasswordUtils.encryptedByMD5(password));
        if (line == 0) {
            return UserStates.ALREADY_EXIST;
        } else {
            return UserStates.SUCCESS;
        }
    }

    /**
     * Find user by username
     *
     * @param username username
     * @return if user not exist, return Optional.empty()
     * @throws SQLException SQL query exception
     */
    private Optional<User> findUserByName(String username) throws SQLException {
        final String sql = "SELECT id, password from user WHERE username = ?";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql, username);
        resultSet.next();
        int id = resultSet.getInt("id");
        String passRight = resultSet.getString("password");
        if (passRight == null) {
            return Optional.empty();
        }
        return Optional.of(new User(id, username, passRight));
    }
}
