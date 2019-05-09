package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.utils.JSONUtils;

/**
 * Music VO Java bean
 */
public class MusicVO {

    /**
     * Singleton failure result object
     */
    public static final MusicVO FAILURE = new MusicVO("", "", JSONUtils.FAILURE);

    /**
     * Music name
     */
    private String name;

    /**
     * Play URL
     */
    private String url;

    /**
     * Successful or failure
     */
    private String states;

    //private List<String> co

    public MusicVO(String name, String url, String states) {
        this.name = name;
        this.url = url;
        this.states = states;
    }

    public MusicVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}
