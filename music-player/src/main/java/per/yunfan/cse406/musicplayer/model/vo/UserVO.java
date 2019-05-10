package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.utils.JSONUtils;

import java.io.Serializable;

/**
 * User VO Java bean
 */
public class UserVO implements Serializable {

    /**
     * Singleton failure result object
     */
    public static final UserVO FAILURE = new UserVO("", "", JSONUtils.FAILURE);

    /**
     * Username
     */
    private String username;

    /**
     * Password or token
     */
    private String password;

    /**
     * Successful or failure
     */
    private String states;

    /**
     * Failure information
     */
    private String info;

    public UserVO(String username, String password, String states) {
        this.username = username;
        this.password = password;
        this.states = states;
    }

    public UserVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
