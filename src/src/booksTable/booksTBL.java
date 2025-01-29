package booksTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class booksTBL {
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

        String createTableSQLBooks = "CREATE TABLE IF NOT EXISTS books (" +
                                "book_id INT PRIMARY KEY, " +
                                "title VARCHAR(255), " +
                                "author VARCHAR(255), " +
                                "genre VARCHAR(100), " +
                                "pages INT, " +
                                "publication_date DATE, " +
                                "availability BOOLEAN)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQLBooks);
            System.out.println("Books table created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}