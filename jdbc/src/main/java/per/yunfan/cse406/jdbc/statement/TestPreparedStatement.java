package per.yunfan.cse406.jdbc.statement;

import per.yunfan.cse406.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用JDBC PreparedStatement的Demo
 */
public class TestPreparedStatement {

    public static void main(String[] args) {

        final String sql = "SELECT student_id, student_name FROM t_student where student_country = ?";

        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            System.out.println("Connected to the database.");

            preparedStatement.setString(1, "China");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("student_name");

                System.out.print("ID: " + id);
                System.out.println(" / Name: " + name);
            }

            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        }

        System.out.println("Completed.");
    }
}
