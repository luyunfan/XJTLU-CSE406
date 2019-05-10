package per.yunfan.cse406.musicplayer.dao.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.dao.MusicDAO;
import per.yunfan.cse406.musicplayer.model.po.Music;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    /**
     * Get all music basically information from database
     *
     * @return (Music id, Music name)
     * @throws SQLException SQL query exception
     */
    @Override
    public Map<Integer, String> getAllMusicInformation() throws SQLException {
        Map<Integer, String> result = new HashMap<>();
        final String sql = "SELECT id, name FROM music;";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            result.put(id, name);
        }
        return result;
    }

    /**
     * Get a music information by music play id
     *
     * @param id Music id
     * @return Music object
     * @throws SQLException SQL query exception
     */
    @Override
    public Optional<Music> getMusicById(int id) throws SQLException {
        final String sql = "SELECT name, path FROM music WHERE id = ?;";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql, id);

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String path = resultSet.getString("path");
            Music result = new Music(id, name, path);
            result.setComments(CommentDAO.getInstance()
                    .getCommentByMusic(result)
            );
            return Optional.of(result);
        }
        return Optional.empty();
    }

}
