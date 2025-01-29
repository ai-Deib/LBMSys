package favoriteBooksTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class faveBooksTBL {
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

        String createTableSQLFaveBooks = "CREATE TABLE IF NOT EXISTS favorite_books (" +
                                         "favorite_id INT PRIMARY KEY, " +
                                         "student_id INT, " +
                                         "book_id INT, " +
                                         "FOREIGN KEY (student_id) REFERENCES users(student_id), " +
                                         "FOREIGN KEY (book_id) REFERENCES books(book_id))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQLFaveBooks);
            System.out.println("Favorite books table created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}
