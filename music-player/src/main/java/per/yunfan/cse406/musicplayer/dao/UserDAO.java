package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.user.UserDAOImpl;
import per.yunfan.cse406.musicplayer.enums.UserStates;
import per.yunfan.cse406.musicplayer.model.po.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public interface UserDAO {

    /**
     * A simple IoC design, it will return the UserDAOImpl object
     *
     * @return object of UserDAO implement class
     */
    static UserDAO instance() {
        return UserDAOImpl.INSTANCE;
    }

    /**
     * Login user method
     *
     * @param userName Input user name
     * @param password Input password
     * @return if login success, return an User object
     * @throws SQLException SQL query exception
     */
    Optional<User> login(String userName, String password) throws SQLException;

    /**
     * Sign in a new user
     *
     * @param userName username
     * @param password password
     * @return Sign in States
     * @throws SQLException SQL update exception
     */
    UserStates signIn(String userName, String password) throws SQLException;

    /**
     * Modify the user's information
     *
     * @param username     Username
     * @param gender       User's gender
     * @param birthday     User's Birthday
     * @param introduction User's introduction
     * @return Is successful
     * @throws SQLException SQL update exception
     */
    boolean modifyUserInfo(String username, char gender, LocalDate birthday, String introduction) throws SQLException;
}
