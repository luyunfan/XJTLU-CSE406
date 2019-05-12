package per.yunfan.cse406.musicplayer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * A simple tool for insert music to database, it support *.mp3 file.
 * <p>
 * Warning: If you run this tool, music table will be cleared!
 */
public class InsertMusicTool {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(InsertMusicTool.class);

    /**
     * Every time deploy project, you need just change your music path in here
     */
    private static final Path musicPath = Paths.get("");

    public static void main(String[] args) {
        clearDatabase();
        traverseFolder(musicPath.toFile(), everyFile -> {
            if (everyFile.isFile() && everyFile.getName().endsWith(".mp3")) {

                try {
                    String musicName = everyFile.getName().replace(".mp3", "");
                    String path = everyFile.getCanonicalPath();
                    Connection connection = JDBCUtils.getConnection();
                    insertAMusic(connection, musicName, path);
                } catch (IOException e) {
                    LOG.error("File path is Invalid: " + everyFile, e);
                } catch (SQLException e) {
                    LOG.error("Insert music: " + everyFile.getName() + " into database failure!", e);
                }
            }
        });
    }

    /**
     * Insert a music to database
     *
     * @param connection JDBC connection
     * @param name       music name
     * @param path       music path
     * @throws SQLException SQL update exception
     */
    private static void insertAMusic(Connection connection, String name, String path) throws SQLException {
        String sql = "INSERT INTO music(name, path) VALUES(?, ?);";
        JDBCUtils.executeUpdate(connection, sql, name, path);
    }

    /**
     * Clean music table
     */
    private static void clearDatabase() {
        String deleteLastData = "DELETE FROM music;";
        try {
            JDBCUtils.executeUpdate(JDBCUtils.getConnection(), deleteLastData);
        } catch (SQLException e) {
            LOG.error("Connect to database failure, please check your jdbc.properties file.", e);
            System.exit(1);
        }
    }

    /**
     * Traverse a folder
     *
     * @param path            folder path
     * @param foundFileAction The action for found each file
     */
    private static void traverseFolder(File path, Consumer<File> foundFileAction) {
        if (path.exists()) {
            LinkedList<File> stack = new LinkedList<>();
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        stack.add(file);
                    }
                    foundFileAction.accept(file);
                }
            }
            while (!stack.isEmpty()) {
                files = stack.removeFirst().listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            stack.add(file);
                        }
                        foundFileAction.accept(file);
                    }
                }
            }
        } else {
            LOG.error("File:" + path + " is not exist!");
        }
    }
}
