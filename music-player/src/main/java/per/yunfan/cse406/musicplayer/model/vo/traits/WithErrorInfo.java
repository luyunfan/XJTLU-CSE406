package per.yunfan.cse406.musicplayer.model.vo.traits;

import java.io.Serializable;

public interface WithErrorInfo<T extends WithErrorInfo<T>> extends Serializable {

    String getErrorInfo();

    T setErrorInfo(String errorInfo);
}
