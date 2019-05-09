package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.music.MusicDAOImpl;
import per.yunfan.cse406.musicplayer.model.Music;

import java.sql.SQLException;
import java.util.List;

public interface MusicDAO {

    /**
     * A simple IoC design, it will return the MusicDAOImpl object
     *
     * @return object of MusicDAO implement class
     */
    static MusicDAO instance() {
        return MusicDAOImpl.INSTANCE;
    }

    /**
     * Get all music from database
     *
     * @return All music list
     * @throws SQLException SQL query exception
     */
    List<Music> getAllMusic() throws SQLException;
}
