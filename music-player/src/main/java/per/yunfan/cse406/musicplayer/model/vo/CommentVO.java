package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.model.vo.traits.WithToken;

public class CommentVO implements WithToken<CommentVO> {
    private String username;

    private String content;

    private String date;

    private int musicId;

    private String token;

    public CommentVO(String username, String content, String date, int musicId, String token) {
        this.token = token;
        this.username = username;
        this.content = content;
        this.date = date;
        this.musicId = musicId;
    }

    public CommentVO() {
        super();
    }

    public String getUsername() {
        return username;
    }

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

    public String getDate() {
        return date;
    }

    public CommentVO setDate(String date) {
        this.date = date;
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
                ", date='" + date + '\'' +
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
