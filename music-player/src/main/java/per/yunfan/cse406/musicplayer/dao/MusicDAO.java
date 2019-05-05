package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.music.MusicDAOImpl;

public interface MusicDAO {

    static MusicDAO instance() {
        return MusicDAOImpl.INSTANCE;
    }
}
