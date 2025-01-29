package notificationTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class notificationTBL {
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

        String createTableSQLNotification = "CREATE TABLE IF NOT EXISTS notification (" +
                                            "notification_id INT PRIMARY KEY, " +
                                            "student_id INT, " +
                                            "message TEXT, " +
                                            "status ENUM('sent', 'pending'), " +
                                            "FOREIGN KEY (student_id) REFERENCES users(student_id))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQLNotification);
            System.out.println("Notification table created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}
