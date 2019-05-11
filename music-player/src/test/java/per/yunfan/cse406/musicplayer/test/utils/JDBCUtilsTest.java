package per.yunfan.cse406.musicplayer.test.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JDBC utility class test")
public class JDBCUtilsTest {


    @Test
    public void testConnection() {
        Connection connection = JDBCUtils.getConnection();
        if (connection == null) {
            fail("Create connection failure");
        }
    }

    @Test
    public void testUpdateAndQuery() throws SQLException {
        String create =
                "CREATE TABLE IF NOT EXISTS test\n" +
                        "(\n" +
                        "    id       INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "    str      VARCHAR(20) NOT NULL,\n" +
                        "    short    SMALLINT    NOT NULL,\n" +
                        "    byte     TINYINT     NOT NULL,\n" +
                        "    lon      BIGINT      NOT NULL,\n" +
                        "    boo      BOOLEAN     NOT NULL,\n" +
                        "    times    TIMESTAMP   NOT NULL,\n" +
                        "    fl       FLOAT       NOT NULL,\n" +
                        "    doubleFl DOUBLE      NOT NULL\n" +
                        ") ENGINE = innodb\n" +
                        "  default charset = utf8mb4;";
        String insert =
                "INSERT INTO test(id, str, short, byte, lon, boo, times, fl, doubleFl) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection connection = JDBCUtils.getConnection();
        if (connection == null) {
            fail("Create connection failure");
        }

        JDBCUtils.executeUpdate(connection, create);
        int id = 1;
        String str = "testStr";
        short sh = Short.MAX_VALUE;
        byte byt = Byte.MAX_VALUE;
        long l = Long.MAX_VALUE;
        boolean bool = true;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        float f = 0.01f;
        double d = 0.01;
        JDBCUtils.executeUpdate(connection,
                insert, id, str,
                sh, byt, l, bool,
                timestamp, f, d);
        String select = "SELECT * FROM test WHERE id = ?;";
        ResultSet resultSet = JDBCUtils.executeQuery(JDBCUtils.getConnection(), select, id);
        if (!resultSet.next()) {
            fail("Not insert into database");
        }
        assertEquals(id, resultSet.getInt("id"));
        assertEquals(str, resultSet.getString("str"));
        assertEquals(sh, resultSet.getShort("short"));
        assertEquals(byt, resultSet.getByte("byte"));
        assertEquals(l, resultSet.getLong("lon"));
        assertEquals(bool, resultSet.getBoolean("boo"));
        assertTrue(Duration.between(timestamp.toLocalDateTime(),
                resultSet.getTimestamp("times").toLocalDateTime()
        ).getSeconds() < 60);
        assertEquals(f, resultSet.getFloat("fl"));
        assertEquals(d, resultSet.getDouble("doubleFl"));
    }

    @AfterAll
    public static void dropDatabase() throws SQLException {
        String sql = "DROP TABLE IF EXISTS test;";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql);
    }
}
