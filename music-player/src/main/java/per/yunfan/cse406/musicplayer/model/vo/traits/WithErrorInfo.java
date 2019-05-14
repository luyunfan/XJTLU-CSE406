package per.yunfan.cse406.musicplayer.model.vo.traits;

import java.io.Serializable;

/**
 * A trait interface that make object with error information
 *
 * @param <T> object type
 */
public interface WithErrorInfo<T extends WithErrorInfo<T>> extends Serializable {

    /**
     * @return Error information text
     */
    String getErrorInfo();

    /**
     * Set  error information text
     *
     * @param errorInfo Error information text
     * @return This object
     */
    T setErrorInfo(String errorInfo);
}
