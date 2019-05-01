package per.yunfan.cse406.jdbc.statement;

import per.yunfan.cse406.jdbc.JdbcUtils;

import java.sql.CallableStatement;
import java.sql.Connection;

public class TestCallableStatement {

    public static void main(String[] args) {

        final String sql = "{call p_compute(?,?)}";

        try (Connection conn = JdbcUtils.getConnection();
             CallableStatement callableStatement = conn.prepareCall(sql)) {

            System.out.println("Connected to the database.");

            callableStatement.setString(1, "China");
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

            callableStatement.execute();

            System.out.println("Count of Chinese student is: "
                    + callableStatement.getInt(2));

        } catch (Exception se) {
            se.printStackTrace();
        }

        System.out.println("Completed.");
    }
}
