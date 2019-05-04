package per.yunfan.cse406.javaee.beans;

import java.io.Serializable;

/**
 * 课堂上Servlet Demo需要使用的封装用户信息的Java Bean
 */
public class User implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private int age;

    /**
     * 国家
     */
    private String country;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
