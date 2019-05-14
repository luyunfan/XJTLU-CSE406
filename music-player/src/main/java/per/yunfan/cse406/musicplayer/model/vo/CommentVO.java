package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.model.vo.traits.WithToken;

/**
 * Comment VO Java bean class
 */
public class CommentVO implements WithToken<CommentVO> {

    /**
     * Comment username
     */
    private String username;

    /**
     * Comment content
     */
    private String content;

    /**
     * Comment datetime string (yyyy-MM-dd HH:mm:ss)
     */
    private String dateTime;

    /**
     * Comment music id
     */
    private int musicId;

    /**
     * User token
     */
    private String token;

    /**
     * CommentVO constructor
     *
     * @param username Comment username
     * @param content  Comment content
     * @param dateTime Comment datetime string (yyyy-MM-dd HH:mm:ss)
     * @param musicId  Comment music id
     * @param token    User token
     */
    public CommentVO(String username, String content, String dateTime, int musicId, String token) {
        this.token = token;
        this.username = username;
        this.content = content;
        this.dateTime = dateTime;
        this.musicId = musicId;
    }

    /**
     * CommentVO constructor with no arguments
     */
    public CommentVO() {
    }

    /**
     * @return Comment username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set comment username
     *
     * @param username Comment username
     * @return This comment object
     */
    public CommentVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public CommentVO setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public int getMusicId() {
        return musicId;
    }

    public CommentVO setMusicId(int musicId) {
        this.musicId = musicId;
        return this;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", musicId=" + musicId +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public CommentVO setToken(String token) {
        this.token = token;
        return this;
    }
}
