package per.yunfan.cse406.musicplayer.dao.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.MusicDAO;
import per.yunfan.cse406.musicplayer.model.Music;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MusicDAO implement class
 */
public enum MusicDAOImpl implements MusicDAO {

    /**
     * Singleton implement by enum
     */
    INSTANCE;

    /**
     * Logger object by log4j2
     */
    private final Logger LOG = LogManager.getLogger(MusicDAOImpl.class);

    /**
     * Get all music from database
     *
     * @return All music list
     * @throws SQLException SQL query exception
     */
    @Override
    public List<Music> getAllMusic() throws SQLException {
        List<Music> result = new ArrayList<>();

        final String sql = "SELECT id, name, path FROM music;";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String path = resultSet.getString("path");
            Music music = new Music(id, name, path);
            music.setComments(CommentDAO.getInstance().getCommentByMusic(music));
            result.add(music);
        }
        return result;
    }

}
