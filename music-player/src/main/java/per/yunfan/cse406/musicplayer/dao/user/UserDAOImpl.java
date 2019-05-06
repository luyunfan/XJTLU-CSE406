package per.yunfan.cse406.musicplayer.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.UserDAO;
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
        final String sql = "SELECT id, password from user WHERE username = ?";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql, userName);
        resultSet.next();
        int id = resultSet.getInt("id");
        String passRight = resultSet.getString("password");
        if (passRight == null || !passRight.equals(PasswordUtils.encryptedByMD5(password))) {
            return Optional.empty();
        }
        return Optional.of(new User(id, userName, passRight));
    }
}
