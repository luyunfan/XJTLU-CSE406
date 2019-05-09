package per.yunfan.cse406.musicplayer.dao.music;

import per.yunfan.cse406.musicplayer.model.Comment;
import per.yunfan.cse406.musicplayer.model.Music;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Comment DAO object
 */
public class CommentDAO {

    /**
     * Singleton object
     */
    private static final CommentDAO commentDAO = new CommentDAO();

    /**
     * Singleton with no constructor
     */
    private CommentDAO() {
    }

    /**
     * @return a Singleton CommentDAO object
     */
    public static CommentDAO getInstance() {
        return commentDAO;
    }

    /**
     * Get a comment list by music object
     *
     * @param music searching music object
     * @return All comment list in this music
     * @throws SQLException SQL query exception
     */
    public List<Comment> getCommentByMusic(Music music) throws SQLException {
        List<Comment> result = new ArrayList<>();
        final String sql = "SELECT id, username, content FROM comment WHERE musicId = ?;";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql, music.getId());

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String content = resultSet.getString("username");
            result.add(new Comment(id, username, music, content));
        }
        return result;
    }
}
