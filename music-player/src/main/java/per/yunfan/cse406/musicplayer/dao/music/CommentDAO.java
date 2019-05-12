package per.yunfan.cse406.musicplayer.dao.music;

import per.yunfan.cse406.musicplayer.model.po.Comment;
import per.yunfan.cse406.musicplayer.model.po.Music;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
        final String sql = "SELECT id, username, content, date FROM comment WHERE musicId = ?;";
        Connection connection = JDBCUtils.getConnection();
        ResultSet resultSet = JDBCUtils.executeQuery(connection, sql, music.getId());

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String content = resultSet.getString("username");
            Timestamp date = resultSet.getTimestamp("date");

            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDate = instant.atZone(zoneId).toLocalDateTime();

            result.add(new Comment(id, username, music, content, localDate));
        }
        return result;
    }

    /**
     * Create a new comment
     *
     * @param comment Comment object
     * @throws SQLException SQL update exception
     */
    public void createComment(Comment comment) throws SQLException {
        final String sql = "INSERT INTO comment(username, musicId, content, date) VALUES(?, ?, ?, ?);";
        String username = comment.getUsername();
        int musicId = comment.getMusic().getId();
        String content = comment.getContent();
        Timestamp date = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        Connection connection = JDBCUtils.getConnection();
        JDBCUtils.executeUpdate(connection, sql, username, musicId, content, date);
    }
}
