package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.music.MusicDAOImpl;
import per.yunfan.cse406.musicplayer.model.po.Music;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /**
     * Get all music basically information from database
     *
     * @return (Music id, Music name)
     * @throws SQLException SQL query exception
     */
    Map<Integer, String> getAllMusicInformation() throws SQLException;

    /**
     * Get a music information by music play id
     *
     * @param id Music id
     * @return Music object
     * @throws SQLException SQL query exception
     */
    Optional<Music> getMusicById(int id) throws SQLException;
}
