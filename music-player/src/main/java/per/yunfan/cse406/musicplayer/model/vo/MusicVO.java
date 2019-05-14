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
     * Play id
     */
    private String playId;

    /**
     * Successful or failure
     */
    private String states;

    /**
     * Music VO constructor
     *
     * @param name   Music name
     * @param playId Play id
     * @param states Successful or failure
     */
    public MusicVO(String name, String playId, String states) {
        this.name = name;
        this.playId = playId;
        this.states = states;
    }

    /**
     * Music VO constructor with no parameter
     */
    public MusicVO() {
    }

    /**
     * @return Music name
     */
    public String getName() {
        return name;
    }

    /**
     * Set music name
     *
     * @param name Music name
     * @return This musicVO object
     */
    public MusicVO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return Play id
     */
    public String getPlayId() {
        return playId;
    }

    /**
     * Set music play id
     *
     * @param playId Music play id
     * @return This musicVO object
     */
    public MusicVO setPlayId(String playId) {
        this.playId = playId;
        return this;
    }

    /**
     * @return Successful or failure states
     */
    public String getStates() {
        return states;
    }

    /**
     * Set states
     *
     * @param states Successful or failure states
     * @return This MusicVO object
     */
    public MusicVO setStates(String states) {
        this.states = states;
        return this;
    }
}
