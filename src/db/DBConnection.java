package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/FREEPDB1",
                "ticket_admin",
                "ticket"
            );
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("DB Connection Failed");
            return null;
        }
    }
}
