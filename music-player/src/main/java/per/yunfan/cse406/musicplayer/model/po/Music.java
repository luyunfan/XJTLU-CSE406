package per.yunfan.cse406.musicplayer.model.po;

import java.io.Serializable;
import java.util.List;

/**
 * Music PO Java bean
 */
public class Music implements Serializable {

    /**
     * Music id in database
     */
    private int id;

    /**
     * Music name
     */
    private String name;

    /**
     * Music path in filesystem
     */
    private String path;

    /**
     * Music comments
     */
    private List<Comment> comments;

    /**
     * Music PO constructor
     *
     * @param id   Music id in database
     * @param name Music name
     * @param path Music path in filesystem
     */
    public Music(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    /**
     * Music constructor with no arguments
     */
    public Music() {
    }

    /**
     * @return Music id in database
     */
    public int getId() {
        return id;
    }

    /**
     * Set music id
     *
     * @param id Music id in database
     * @return This music object
     */
    public Music setId(int id) {
        this.id = id;
        return this;
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
     * @return This music object
     */
    public Music setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return Music path in filesystem
     */
    public String getPath() {
        return path;
    }

    /**
     * Set music path in filesystem
     *
     * @param path Music path in filesystem
     * @return This music object
     */
    public Music setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * @return Music comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Set music comments
     *
     * @param comments Music comments
     * @return This music object
     */
    public Music setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
