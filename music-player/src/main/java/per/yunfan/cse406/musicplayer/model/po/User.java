package per.yunfan.cse406.musicplayer.model.po;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * User PO Java bean class
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
     * User gender, f = female, m = male, n = known, o = other
     */
    private char gender;

    /**
     * User's birthday
     */
    private LocalDate birthday;

    /**
     * User's self introduction
     */
    private String introduction;

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
     * @return This user object
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * User name setter
     *
     * @param userName User name
     * @return This user object
     */
    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * User password setter
     *
     * @param password User password
     * @return This User object
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
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

    /**
     * @return User gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * Set user gender
     *
     * @param gender User gender
     * @return This user object
     */
    public User setGender(char gender) {
        this.gender = gender;
        return this;
    }

    /**
     * @return User's birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Set user's birthday
     *
     * @param birthday User's birthday
     * @return This user object
     */
    public User setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    /**
     * @return User's introduction text
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Set user's introduction text
     *
     * @param introduction User's introduction text
     * @return This user object
     */
    public User setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }
}
