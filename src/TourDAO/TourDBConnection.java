package TourDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TourDBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/TravelBookingSystemApp"; 
    private static String USER = "root";   
    private static String PASSWORD = "12345khongcho";
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
    }

}