package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSQLConnection {
    private final String SERVERNAME = "TAMIDA142857";
    private final String DBNAME = "APINAME"; 
    private final String PORT = "1433";
    private final String USERID = "Tamida";               
    private final String PASSWORD = "123456";          
    private final String INSTANCE = "";              

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String url;
        if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORT + ";databaseName=" + DBNAME;
        } else {
            url = "jdbc:sqlserver://" + SERVERNAME + "\\" + INSTANCE + ":" + PORT + ";databaseName=" + DBNAME;
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, USERID, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            DBSQLConnection db = new DBSQLConnection();
            Connection conn = db.getConnection();
            if (conn != null) {
                System.out.println("✅ Kết nối SQL Server thành công!");
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy driver JDBC SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi kết nối SQL Server.");
            e.printStackTrace();
        }
    }
}
