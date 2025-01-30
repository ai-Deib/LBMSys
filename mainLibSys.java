import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class mainLibSys {
    public static void main(String[] args) {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Establish the connection
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "alexander_0114";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connection established successfully!");

            boolean continueInput = true;

            while (continueInput) {
                System.out.println("==Choose an option to insert data==");
                System.out.println("1. Insert into users table");
                System.out.println("2. Insert into books table");
                System.out.println("3. Insert into borrow_history table");
                System.out.println("4. Insert into favorite_books table");
                System.out.println("5. Insert into notification table");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        // Take user input for the users table
                        System.out.println("Enter student_id, name, section, role, email, password for users table:");
                        int studentId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        String name = scanner.nextLine();
                        String section = scanner.nextLine();
                        String role = scanner.nextLine();
                        String email = scanner.nextLine();
                        String userPassword = scanner.nextLine();

                        String insertUsersSQL = String.format("INSERT INTO users (student_id, name, section, role, email, password) VALUES (%d, '%s', '%s', '%s', '%s', '%s')",
                                                              studentId, name, section, role, email, userPassword);
                        statement.executeUpdate(insertUsersSQL);
                        System.out.println("Data inserted into users table successfully!");
                        break;

                    case 2:
                        // Take user input for the books table
                        System.out.println("Enter book_id, title, author, genre, pages, publication_date (YYYY-MM-DD), availability (true/false) for books table:");
                        int bookId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        String title = scanner.nextLine();
                        String author = scanner.nextLine();
                        String genre = scanner.nextLine();
                        int pages = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        String publicationDate = scanner.nextLine();
                        boolean availability = scanner.nextBoolean();

                        String insertBooksSQL = String.format("INSERT INTO books (book_id, title, author, genre, pages, publication_date, availability) VALUES (%d, '%s', '%s', '%s', %d, '%s', %b)",
                                                              bookId, title, author, genre, pages, publicationDate, availability);
                        statement.executeUpdate(insertBooksSQL);
                        System.out.println("Data inserted into books table successfully!");
                        break;

                    case 3:
                        // Take user input for the borrow_history table
                        System.out.println("Enter history_id, student_id, book_id, borrow_date (YYYY-MM-DD), return_date (YYYY-MM-DD), actual_return_date (YYYY-MM-DD), penalty for borrow_history table:");
                        int historyId = scanner.nextInt();
                        int bhStudentId = scanner.nextInt();
                        int bhBookId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        String borrowDate = scanner.nextLine();
                        String returnDate = scanner.nextLine();
                        String actualReturnDate = scanner.nextLine();
                        double penalty = scanner.nextDouble();

                        String insertBorrowHistorySQL = String.format("INSERT INTO borrow_history (history_id, student_id, book_id, borrow_date, return_date, actual_return_date, penalty) VALUES (%d, %d, %d, '%s', '%s', '%s', %.2f)",
                                                                      historyId, bhStudentId, bhBookId, borrowDate, returnDate, actualReturnDate, penalty);
                        statement.executeUpdate(insertBorrowHistorySQL);
                        System.out.println("Data inserted into borrow_history table successfully!");
                        break;

                    case 4:
                        // Take user input for the favorite_books table
                        System.out.println("Enter favorite_id, student_id, book_id for favorite_books table:");
                        int favoriteId = scanner.nextInt();
                        int fbStudentId = scanner.nextInt();
                        int fbBookId = scanner.nextInt();

                        String insertFavoriteBooksSQL = String.format("INSERT INTO favorite_books (favorite_id, student_id, book_id) VALUES (%d, %d, %d)",
                                                                      favoriteId, fbStudentId, fbBookId);
                        statement.executeUpdate(insertFavoriteBooksSQL);
                        System.out.println("Data inserted into favorite_books table successfully!");
                        break;

                    case 5:
                        // Take user input for the notification table
                        System.out.println("Enter notification_id, student_id, message, status (sent/pending) for notification table:");
                        int notificationId = scanner.nextInt();
                        int nStudentId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        String message = scanner.nextLine();
                        String status = scanner.nextLine();

                        String insertNotificationSQL = String.format("INSERT INTO notification (notification_id, student_id, message, status) VALUES (%d, %d, '%s', '%s')",
                                                                     notificationId, nStudentId, message, status);
                        statement.executeUpdate(insertNotificationSQL);
                        System.out.println("Data inserted into notification table successfully!");
                        break;

                    case 6:
                        continueInput = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }
}