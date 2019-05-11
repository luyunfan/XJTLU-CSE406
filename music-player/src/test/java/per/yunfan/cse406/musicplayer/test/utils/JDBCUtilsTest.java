package per.yunfan.cse406.musicplayer.test.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("JDBC utility class test")
public class JDBCUtilsTest {

    @Test
    public void testConnection() {
        Connection connection = JDBCUtils.getConnection();
        if (connection == null) {
            fail("Create connection failure");
        }
    }


}
