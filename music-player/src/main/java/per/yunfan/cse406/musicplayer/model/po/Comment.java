package per.yunfan.cse406.musicplayer.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Comment PO Java bean
 */
public class Comment implements Serializable {

    /**
     * Comment id in database
     */
    private int id;

    /**
     * Username of this comment
     */
    private String username;

    /**
     * Music of this comment
     */
    private Music music;

    /**
     * Comment content
     */
    private String content;

    /**
     * Comment date
     */
    private LocalDateTime date;

    /**
     * Constructor of comment object
     *
     * @param id       Comment id in database
     * @param username Username of this comment
     * @param music    Music of this comment
     * @param content  Comment content
     * @param date     Comment date
     */
    public Comment(int id, String username, Music music, String content, LocalDateTime date) {
        this.id = id;
        this.username = username;
        this.music = music;
        this.content = content;
        this.date = date;
    }

    /**
     * @return Comment id in database
     */
    public int getId() {
        return id;
    }

    /**
     * Set comment id
     *
     * @param id Comment id in database
     * @return this comment object
     */
    public Comment setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return Username of this comment
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of this comment
     *
     * @param username Username of this comment
     * @return this comment object
     */
    public Comment setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return Music of this comment
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Set music
     *
     * @param music Music of this comment
     * @return this comment object
     */
    public Comment setMusic(Music music) {
        this.music = music;
        return this;
    }

    /**
     * @return Comment content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set comment content
     *
     * @param content Comment content
     * @return this comment object
     */
    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * @return Comment date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Set comment date
     *
     * @param date Comment date
     * @return this comment object
     */
    public Comment setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
