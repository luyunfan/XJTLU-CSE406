package per.yunfan.cse406.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 一个JDBC的工具类，用于获取配置文件的配置建立连接，以及提供通用的SQL执行逻辑
 */
public final class JdbcUtils {

    /**
     * 工具类不能实例化
     */
    private JdbcUtils() {
    }

    /**
     * 根据配置信息获取Connection对象
     *
     * @return JDBC Connection对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties props = new Properties();
            props.load(JdbcUtils.class.getResourceAsStream("/jdbc.properties"));
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            if (!DriverManager.getDrivers().hasMoreElements()) {
                Class.forName(props.getProperty("jdbc.driver"));
            }
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Connection SQL server failure!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Load per.yunfan.cse406.jdbc.properties file failure!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Register JDBC driver failure!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 执行一个更新SQL语句
     *
     * @param connection JDBC Connection对象
     * @param sql        SQL语句
     * @param para       SQL语句中包含的"?"参数的实际值
     * @return 更新SQL影响的行数
     * @throws SQLException 出现SQL执行的异常
     */
    @SuppressWarnings("Duplicates")
    public static int executeUpdate(Connection connection,
                                    String sql,
                                    Object... para) throws SQLException {
        int resCount;
        if (sql == null || sql.isEmpty()) {
            throw new SQLException("SQL could not be null or empty !");
        }
        System.out.println("sql execute --> " + sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        insertParameterToPrepareStatement(ps, para);
        resCount = ps.executeUpdate();
        return resCount;
    }

    /**
     * 执行一个查询SQL语句
     *
     * @param connection JDBC Connection对象
     * @param sql        SQL语句
     * @param para       SQL语句中包含的"?"参数的实际值
     * @return 查询后得到的ResultSet对象
     * @throws SQLException 出现SQL执行的异常
     */
    @SuppressWarnings("Duplicates")
    public static ResultSet executeQuery(Connection connection,
                                         String sql,
                                         Object... para) throws SQLException {
        if (sql == null || sql.isEmpty()) {
            throw new SQLException("SQL could not be null or empty !");
        }
        System.out.println("sql execute --> " + sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        insertParameterToPrepareStatement(ps, para);
        return ps.executeQuery();
    }

    /**
     * 辅助查询和更新SQL中对动态参数的插入方法
     *
     * @param preparedStatement 需要插入参数的PreparedStatement对象
     * @param para              参数
     * @throws SQLException 出现SQL执行的异常
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
            } else {
                throw new SQLException("Not support parameter type: " + value.getClass() + " now.");
            }
        }
    }

}
