package per.yunfan.cse406.jdbc.statement;

import per.yunfan.cse406.jdbc.JdbcUtils;

import java.sql.*;

/**
 * 2019-3-25课堂练习
 */
public class ClassPractice2 {

    private static final String CREATE_AIRPORT_TABLE =
            "CREATE TABLE IF NOT EXISTS airport (" +
                    "airport_code int(11) PRIMARY KEY, " +
                    "airport_name varchar(20), " +
                    "city varchar(20)" +
                    ") DEFAULT CHARSET = utf8;";

    private static final String CREATE_FLIGHT_TABLE =
            "CREATE TABLE IF NOT EXISTS flight (" +
                    "flight_number int(11) PRIMARY KEY, " +
                    "origin_airport_code int(11), " +
                    "destination_airport_code int(11)" +
                    ") DEFAULT CHARSET = utf8;";

    private static final String INSERT_ORIGINAL_AIRPORT_1 =
            "INSERT INTO airport VALUES(1, '虹桥', 'Shanghai');";

    private static final String INSERT_ORIGINAL_AIRPORT_2 =
            "INSERT INTO airport VALUES(2, '双流', 'Chengdu');";

    private static final String ADD_FLIGHT_WITH_STATEMENT =
            "INSERT INTO flight VALUES(1, 1, 2);";

    private static final String CHECK_IS_FIRST_SQL =
            "SELECT COUNT(airport_code) FROM airport;";

    private static final String SEARCH_AIRPORT_CODE_BY_NAME =
            "SELECT airport_code FROM airport WHERE airport_name = ?";

    private static final String SEARCH_FLIGHTS_SQL =
            "SELECT * FROM flight WHERE destination_airport_code = ?";

    private static final String CREATE_PROCEDURE =
            "CREATE PROCEDURE `amount_of_flights_by_airport` (IN airportName VARCHAR(20), OUT amount INT) " +
                    "BEGIN " +
                    "  DECLARE code INT;" +
                    "  SET code = (SELECT airport_code FROM airport WHERE airport_name = airportName);" +
                    "  SELECT COUNT(*) INTO amount FROM flight WHERE destination_airport_code = code;" +
                    "END;";

    private static final String DROP_CREATE_PROCEDURE =
            "DROP PROCEDURE IF EXISTS amount_of_flights_by_airport";

    public static void main(String[] args) {
        try (Connection connection = JdbcUtils.getConnection()) {
            int lineAirportCreate = JdbcUtils
                    .executeUpdate(connection, CREATE_AIRPORT_TABLE);

            System.out.println("update for create airport table change "
                    + lineAirportCreate + " lines");

            int lineFlightCreate = JdbcUtils
                    .executeUpdate(connection, CREATE_FLIGHT_TABLE);

            System.out.println("update for create flight table change "
                    + lineFlightCreate + " lines");

            if (isFirstRun(connection)) {
                Statement createFlight = connection.createStatement();
                int countAir1 = createFlight.executeUpdate(INSERT_ORIGINAL_AIRPORT_1);
                int countAir2 = createFlight.executeUpdate(INSERT_ORIGINAL_AIRPORT_2);
                int countFlight = createFlight.executeUpdate(ADD_FLIGHT_WITH_STATEMENT);
                System.out.println("Add airport1 success with " + countAir1 + " line.");
                System.out.println("Add airport2 success with " + countAir2 + " line.");
                System.out.println("Add flight success with " + countFlight + " line.");
            } else {
                JdbcUtils.executeUpdate(connection, DROP_CREATE_PROCEDURE);
            }

            ResultSet airPortSet = JdbcUtils.executeQuery(connection, SEARCH_AIRPORT_CODE_BY_NAME, "虹桥");
            airPortSet.next();
            int code = airPortSet.getInt("airport_code");

            ResultSet resultSet = JdbcUtils.executeQuery(connection, SEARCH_FLIGHTS_SQL, code);

            while (resultSet.next()) {
                int flightNumber = resultSet.getInt("flight_number");
                int originCode = resultSet.getInt("origin_airport_code");
                int destinationCode = resultSet.getInt("destination_airport_code");
                System.out.println("flight number: " + flightNumber);
                System.out.println("origin code: " + originCode);
                System.out.println("destination code: " + destinationCode);
            }

            JdbcUtils.executeUpdate(connection, CREATE_PROCEDURE);

            CallableStatement callableStatement = connection.prepareCall("{call amount_of_flights_by_airport(?,?)}");

            callableStatement.setString(1, "双流");
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

            callableStatement.execute();

            System.out.println("Count of flights to 双流 is: "
                    + callableStatement.getInt(2));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFirstRun(Connection connection) throws SQLException {
        ResultSet resultSet = JdbcUtils
                .executeQuery(connection, CHECK_IS_FIRST_SQL);

        resultSet.next();
        return resultSet.getInt("COUNT(airport_code)") == 0;
    }
}
