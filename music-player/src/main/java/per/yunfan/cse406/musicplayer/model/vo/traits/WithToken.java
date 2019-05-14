package per.yunfan.cse406.musicplayer.model.vo.traits;

import java.io.Serializable;

/**
 * A trait interface that make object with token information
 *
 * @param <T> object type
 */
public interface WithToken<T extends WithToken<T>> extends Serializable {

    /**
     * @return Token information
     */
    String getToken();

    /**
     * Set token information
     *
     * @param token Token information
     * @return This object
     */
    T setToken(String token);
}
