package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.model.vo.traits.WithErrorInfo;
import per.yunfan.cse406.musicplayer.model.vo.traits.WithToken;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

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

    public void setStates(String states) {
        this.states = states;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
