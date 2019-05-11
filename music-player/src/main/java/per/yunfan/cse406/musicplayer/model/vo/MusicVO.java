package per.yunfan.cse406.musicplayer.model.vo;

import per.yunfan.cse406.musicplayer.utils.JSONUtils;

import java.io.Serializable;

/**
 * Music VO Java bean
 */
public class MusicVO implements Serializable {

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
    private String playId;

    /**
     * Successful or failure
     */
    private String states;


    public MusicVO(String name, String playId, String states) {
        this.name = name;
        this.playId = playId;
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

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}
