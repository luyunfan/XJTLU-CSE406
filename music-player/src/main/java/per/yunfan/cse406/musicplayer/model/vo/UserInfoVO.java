package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.model.vo.traits.WithErrorInfo;
import per.yunfan.cse406.musicplayer.model.vo.traits.WithToken;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

/**
 * Additional userVO information Java bean class
 */
public class UserInfoVO implements WithToken<UserInfoVO>, WithErrorInfo<UserInfoVO> {

    /**
     * A singleton failure object
     */
    public static final UserInfoVO FAILURE = new UserInfoVO(JSONUtils.FAILURE);

    /**
     * User gender, f = female, m = male, n = known, o = other
     */
    private char gender;

    /**
     * User's birthday
     */
    private String birthday;

    /**
     * User's self introduction
     */
    private String introduction;

    /**
     * User token
     */
    private String token;

    /**
     * Error information
     */
    private String errorInfo;

    public String getStates() {
        return states;
    }

    public UserInfoVO setStates(String states) {
        this.states = states;
        return this;
    }

    public char getGender() {
        return gender;
    }

    public UserInfoVO setGender(char gender) {
        this.gender = gender;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public UserInfoVO setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public UserInfoVO setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    /**
     * Successful or failure
     */
    private String states;

    public UserInfoVO() {
    }

    public UserInfoVO(char gender, String birthday, String introduction) {
        this.gender = gender;
        this.birthday = birthday;
        this.introduction = introduction;
    }

    public UserInfoVO(String states) {
        this.states = states;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public UserInfoVO setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String getErrorInfo() {
        return this.errorInfo;
    }

    @Override
    public UserInfoVO setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }
}
