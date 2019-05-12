package per.yunfan.cse406.musicplayer.model.vo.traits;

import java.io.Serializable;

public interface WithToken<T extends WithToken<T>> extends Serializable {


    String getToken();

    T setToken(String token);
}
