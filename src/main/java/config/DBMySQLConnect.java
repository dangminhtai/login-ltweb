package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySQLConnect {
	private static String URL = "jdbc:mysql://localhost:3306/ltweb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static String USER_NAME = "root";
    private static String PASSWORD = "12345";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found");
        }

        conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        System.out.println("Connected to the database!");
        return conn;
    }
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {  // tự động close connection
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }
}
