package bookIMG;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class bookImgs {
    public static void main(String[] args) {
        //Insert the path of the image files you want to insert into the book_images table
        //Change the path to the location of the image files on your machine
        createBookImagesTable();
        insertBookImage(1, "C:/Users/Admin/Documents/LBMSys/src/img/Power_of_the_Mind.jpg");
        insertBookImage(2, "C:/Users/Admin/Documents/LBMSys/src/img/Psychology_of_Money.jpg");
        insertBookImage(3, "C:/Users/Admin/Documents/LBMSys/src/img/Tao_of_Physics.jpg");
        insertBookImage(4, "C:/Users/Admin/Documents/LBMSys/src/img/Zero_to_One.jpg");
        insertBookImage(5, "C:/Users/Admin/Documents/LBMSys/src/img/Physics_for_Sci_and_Engrs.jpg");
        insertBookImage(6, "C:/Users/Admin/Documents/LBMSys/src/img/Art_of_War.jpg");
        insertBookImage(7, "C:/Users/Admin/Documents/LBMSys/src/img/Principles_of_Marketing.jpg");
        insertBookImage(8, "C:/Users/Admin/Documents/LBMSys/src/img/Marketing_Management.jpg");
        insertBookImage(9, "C:/Users/Admin/Documents/LBMSys/src/img/Eastern_Europe.jpg");
        insertBookImage(10, "C:/Users/Admin/Documents/LBMSys/src/img/Mathematical_Proofs.jpg");
        insertBookImage(11, "C:/Users/Admin/Documents/LBMSys/src/img/Graphic_Design.jpg");
        insertBookImage(12, "C:/Users/Admin/Documents/LBMSys/src/img/Production_of_Graphic_Designers.jpg");
        insertBookImage(13, "C:/Users/Admin/Documents/LBMSys/src/img/Intelligent_Investor.jpg");
        insertBookImage(14, "C:/Users/Admin/Documents/LBMSys/src/img/The_Millionare_Next_Door.jpg");
        insertBookImage(15, "C:/Users/Admin/Documents/LBMSys/src/img/Advances_in_Computers.jpg");
        insertBookImage(16, "C:/Users/Admin/Documents/LBMSys/src/img/Computational_Linguistics.jpg");
        insertBookImage(17, "C:/Users/Admin/Documents/LBMSys/src/img/Gen_Chem.jpg");
        insertBookImage(18, "C:/Users/Admin/Documents/LBMSys/src/img/Inductory_of_Chemistry.jpg");
        insertBookImage(19, "C:/Users/Admin/Documents/LBMSys/src/img/The_Age_of_the_Surveillance_Capitalism.jpg");
        insertBookImage(20, "C:/Users/Admin/Documents/LBMSys/src/img/21_Lessons_for_the_21st_Century.jpg");

    }

    public static void createBookImagesTable() {
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

        String createTableSQLBookImgs = "CREATE TABLE IF NOT EXISTS book_images (" +
                                        "image_id INT PRIMARY KEY AUTO_INCREMENT, " +
                                        "book_id INT, " +
                                        "image BLOB, " +
                                        "FOREIGN KEY (book_id) REFERENCES books(book_id))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection established successfully!");

            statement.execute(createTableSQLBookImgs);
            System.out.println("Book images table created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to establish connection or execute SQL statement.");
            e.printStackTrace();
        }
    }

    public static void insertBookImage(int bookId, String imagePath) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "alexander_0114";

        String insertImageSQL = "INSERT INTO book_images (book_id, image) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertImageSQL)) {

            preparedStatement.setInt(1, bookId);

            // Read the image file and set it as a BLOB
            try (FileInputStream fis = new FileInputStream(imagePath)) {
                preparedStatement.setBinaryStream(2, fis, fis.available());
                preparedStatement.executeUpdate();
            }

            System.out.println("Image inserted into book_images table successfully!");

        } catch (SQLException | IOException e) {
            System.out.println("Failed to insert image into book_images table.");
            e.printStackTrace();
        }
    }
}
