package adminTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class admin {
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

        String createTableSQLAdmin = "CREATE TABLE IF NOT EXISTS admin (" +
                                     "username VARCHAR(255) PRIMARY KEY, " +
                                     "password VARCHAR(255))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQLAdmin);
            System.out.println("Admin table created successfully!");

            // Insert sample data into the admin table
            String insertAdminSQL = "INSERT INTO admin (username, password) VALUES " +
                                    "('admin1', 'admin1'), " +
                                    "('admin2', 'admin2')";
            statement.executeUpdate(insertAdminSQL);
            System.out.println("Sample data inserted into admin table successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}