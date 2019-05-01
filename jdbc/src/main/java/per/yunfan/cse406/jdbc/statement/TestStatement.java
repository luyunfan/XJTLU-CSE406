package per.yunfan.cse406.jdbc.statement;

import per.yunfan.cse406.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestStatement {

    public static void main(String[] args) {

        final String sql = "SELECT student_id, student_name, student_country FROM t_student";

        try (Connection conn = JdbcUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Connected to the database.");

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("student_name");
                String hometown = rs.getString("student_country");

                System.out.print("ID: " + id);
                System.out.print(" / Name: " + name);
                System.out.println(" / Hometown: " + hometown);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Completed.");
    }
}
