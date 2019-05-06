package per.yunfan.cse406.musicplayer.model;

import java.io.Serializable;

/**
 * User Java bean class
 */
public class User implements Serializable {

    /**
     * User id
     */
    private int id;

    /**
     * User name
     */
    private String userName;

    /**
     * User password
     */
    private String password;

    /**
     * User constructor
     *
     * @param id       User id
     * @param userName User name
     * @param password User password
     */
    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    /**
     * User constructor with no arguments
     */
    public User() {
    }

    /**
     * User id setter
     *
     * @param id User id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * User name setter
     *
     * @param userName User name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * User password setter
     *
     * @param password User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return User id
     */
    public int getId() {
        return id;
    }

    /**
     * @return User name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return User password
     */
    public String getPassword() {
        return password;
    }
}
