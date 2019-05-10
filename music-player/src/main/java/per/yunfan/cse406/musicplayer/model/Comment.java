package per.yunfan.cse406.musicplayer.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {

    private int id;

    private String username;

    private Music music;

    private String content;

    private LocalDateTime date;

    public Comment(int id, String username, Music music, String content, LocalDateTime date) {
        this.id = id;
        this.username = username;
        this.music = music;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
