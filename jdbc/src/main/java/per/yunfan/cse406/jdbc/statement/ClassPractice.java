package per.yunfan.cse406.jdbc.statement;

import per.yunfan.cse406.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * 2019-3-14课堂练习
 */
public class ClassPractice {

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS t_teacher (" +
                    "teacher_id int(11) PRIMARY KEY, " +
                    "teacher_name varchar(20), " +
                    "country varchar(20)" +
                    ") DEFAULT CHARSET = utf8;";

    private static final String INSERT_SQL_1 =
            "INSERT INTO t_teacher VALUES (1, 'Hongfei', 'China');";

    private static final String INSERT_SQL_2 =
            "INSERT INTO t_teacher VALUES (2, 'Joe', 'UK');";

    private static final String SELECT_SQL = "SELECT teacher_id, teacher_name FROM t_teacher where country = ?";

    private static final String SELECT_ID_SQL = "SELECT teacher_id FROM t_teacher where teacher_id = 1 or teacher_id = 2";

    public static void main(String[] args) {
        try (Connection connection = JdbcUtils.getConnection()) {
            int lineCreate = JdbcUtils
                    .executeUpdate(connection, CREATE_TABLE_SQL);
            System.out.println("update for create table change " + lineCreate + " lines");

            ResultSet hasTeacher = JdbcUtils.executeQuery(connection, SELECT_ID_SQL);
            if (!hasTeacher.next()) {
                int lineInsert = JdbcUtils
                        .executeUpdate(connection, INSERT_SQL_1);
                lineInsert += JdbcUtils.executeUpdate(connection, INSERT_SQL_2);
                System.out.println("update for insert table change " + lineInsert + " lines");
            }

            ResultSet rsUk = JdbcUtils.executeQuery(connection, SELECT_SQL, "uk");
            while (rsUk.next()) {
                int id = rsUk.getInt("teacher_id");
                String name = rsUk.getString("teacher_name");
                System.out.println("ID: " + id + " / Name: " + name);
            }

            hasTeacher.close();
            rsUk.close();
        } catch (Exception se) {
            se.printStackTrace();
        }

        System.out.println("Completed.");
    }
}
