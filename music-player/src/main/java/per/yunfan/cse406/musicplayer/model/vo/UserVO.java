package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.model.vo.traits.WithErrorInfo;
import per.yunfan.cse406.musicplayer.model.vo.traits.WithToken;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

/**
 * User VO Java bean
 */
public class UserVO implements WithErrorInfo<UserVO>, WithToken<UserVO> {

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
     * Error information
     */
    private String errorInfo;

    /**
     * Token
     */
    private String token;


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

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getStates() {
        return states;
    }

    public UserVO setStates(String states) {
        this.states = states;
        return this;
    }

    @Override
    public String getErrorInfo() {
        return this.errorInfo;
    }

    @Override
    public UserVO setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public UserVO setToken(String token) {
        this.token = token;
        return this;
    }
}
