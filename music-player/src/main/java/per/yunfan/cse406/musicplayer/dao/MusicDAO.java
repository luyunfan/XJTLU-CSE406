package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.music.MusicDAOImpl;

public interface MusicDAO {

    /**
     * A simple IoC design, it will return the MusicDAOImpl object
     *
     * @return object of MusicDAO implement class
     */
    static MusicDAO instance() {
        return MusicDAOImpl.INSTANCE;
    }
}
