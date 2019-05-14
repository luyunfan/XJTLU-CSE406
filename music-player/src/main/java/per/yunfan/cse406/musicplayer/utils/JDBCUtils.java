package per.yunfan.cse406.musicplayer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 * JDBC utility class
 */
public final class JDBCUtils {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(JDBCUtils.class);

    /**
     * Utility class can't create instance
     */
    private JDBCUtils() {
    }

    /**
     * Get JDBC Connection object by jdbc.properties
     *
     * @return JDBC Connection object
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties props = new Properties();
            props.load(JDBCUtils.class.getResourceAsStream("/jdbc.properties"));
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            if (!DriverManager.getDrivers().hasMoreElements()) {
                Class.forName(props.getProperty("jdbc.driver"));
            }
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOG.error("Connection SQL server failure!", e);
        } catch (IOException e) {
            LOG.error("Load jdbc.properties file failure!", e);
        } catch (ClassNotFoundException e) {
            LOG.error("Register JDBC driver failure!", e);
        }
        return connection;
    }

    /**
     * Execute an update SQL statement
     *
     * @param connection JDBC Connection object
     * @param sql        SQL statement string
     * @param para       The parameter of SQL statement
     * @return Number of rows affected after the update
     * @throws SQLException SQL exception
     */
    public static int executeUpdate(Connection connection,
                                    String sql,
                                    Object... para) throws SQLException {
        int resCount;
        if (sql == null || sql.isEmpty()) {
            throw new SQLException("SQL could not be null or empty !");
        }
        LOG.info("sql execute --> " + sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        insertParameterToPrepareStatement(ps, para);
        resCount = ps.executeUpdate();
        return resCount;
    }

    /**
     * Execute a query SQL statement
     *
     * @param connection JDBC Connection object
     * @param sql        SQL statement string
     * @param para       The parameter of SQL statement
     * @return ResultSet object obtained after query
     * @throws SQLException SQL exception
     */
    public static ResultSet executeQuery(Connection connection,
                                         String sql,
                                         Object... para) throws SQLException {
        if (sql == null || sql.isEmpty()) {
            throw new SQLException("SQL could not be null or empty !");
        }
        LOG.info("sql execute --> " + sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        insertParameterToPrepareStatement(ps, para);
        return ps.executeQuery();
    }

    /**
     * Auxiliary query and update method for inserting dynamic parameters in SQL
     *
     * @param preparedStatement PreparedStatement object that needs to insert a parameter
     * @param para              parameter
     * @throws SQLException SQL exception
     */
    private static void insertParameterToPrepareStatement(
            PreparedStatement preparedStatement,
            Object... para) throws SQLException {

        for (int i = 1; i < para.length + 1; i++) {
            Object value = para[i - 1];
            if (value instanceof String) {
                preparedStatement.setString(i, (String) para[i - 1]);
            } else if (value instanceof Integer) {
                preparedStatement.setInt(i, (Integer) para[i - 1]);
            } else if (value instanceof Float) {
                preparedStatement.setFloat(i, (Float) para[i - 1]);
            } else if (value instanceof Double) {
                preparedStatement.setDouble(i, (Double) para[i - 1]);
            } else if (value instanceof Byte) {
                preparedStatement.setByte(i, (Byte) para[i - 1]);
            } else if (value instanceof Short) {
                preparedStatement.setShort(i, (Short) para[i - 1]);
            } else if (value instanceof Long) {
                preparedStatement.setLong(i, (Long) para[i - 1]);
            } else if (value instanceof Boolean) {
                preparedStatement.setBoolean(i, (Boolean) para[i - 1]);
            } else if (value instanceof Timestamp) {
                preparedStatement.setTimestamp(i, (Timestamp) value);
            } else if (value instanceof Character) {
                preparedStatement.setString(i, String.valueOf(value));
            } else if (value instanceof LocalDate) {
                preparedStatement.setDate(i, Date.valueOf((LocalDate) value));
            } else {
                throw new SQLException("Not support parameter type: " + value.getClass() + " now.");
            }
        }
    }

}
