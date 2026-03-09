package atmapp;
import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/atmdb",
                "root","" 
            );
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
}