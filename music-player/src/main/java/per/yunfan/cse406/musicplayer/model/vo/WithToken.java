package per.yunfan.cse406.musicplayer.model.vo;

public abstract class WithToken {
    protected String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WithToken(String token) {
        this.token = token;
    }

    public WithToken() {
    }
}
