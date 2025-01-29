package usersLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class userLMBS {
    public static void main(String[] args) {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Establish the connection and create the table
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "alexander_0114";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                "student_id INT PRIMARY KEY, " +
                                "name VARCHAR(255), " +
                                "section VARCHAR(100), " +
                                "role ENUM('student', 'admin'), " +
                                "email VARCHAR(255), " +
                                "password VARCHAR(255))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQL);
            System.out.println("Table created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}