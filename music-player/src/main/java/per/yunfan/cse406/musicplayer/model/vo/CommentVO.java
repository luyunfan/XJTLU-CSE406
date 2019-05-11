package per.yunfan.cse406.musicplayer.model.vo;

import java.io.Serializable;

public class CommentVO extends WithToken implements Serializable {
    private String username;

    private String content;

    private String date;

    private int musicId;

    public CommentVO(String username, String content, String date, int musicId, String token) {
        super(token);
        this.username = username;
        this.content = content;
        this.date = date;
        this.musicId = musicId;
    }

    public CommentVO(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", musicId=" + musicId +
                ", token='" + token + '\'' +
                '}';
    }
}
