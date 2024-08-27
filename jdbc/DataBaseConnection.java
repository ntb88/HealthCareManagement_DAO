package HealthCareDAO101.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/HealthcareManagementDB";
    static final String user = "root";
    static final String password = "Arukani12!";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
