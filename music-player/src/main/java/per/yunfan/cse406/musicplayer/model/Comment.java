package per.yunfan.cse406.musicplayer.model;

public class Comment {

    private int id;

    private String username;

    private Music music;

    private String content;

    public Comment(int id, String username, Music music, String content) {
        this.id = id;
        this.username = username;
        this.music = music;
        this.content = content;
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
}
