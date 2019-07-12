package jdbc;



import java.sql.*;

import static java.lang.Class.forName;

public class JDBCConnectionLessons {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root";
        String connectionURl = "jdbc:mysql://localhost:3306/test";
        forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionURl, userName, password);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE Books");
            statement.executeUpdate("CREATE TABLE Books (id SERIAL, name VARCHAR(30) NOT NULL , PRIMARY KEY (id) )");
            statement.executeUpdate("INSERT INTO Books (name) VALUES('Inferno')");
            statement.executeUpdate("INSERT INTO Books (name) VALUES('Solomon key')");
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM Books");

            while (resultSet.next()){
//                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString("name"));

                System.out.println("-------------------");
            }

            System.out.println("__________________________");

            ResultSet rs2 = statement.executeQuery("SELECT  name FROM books WHERE id = 1");
            while(rs2.next()) {
                System.out.println(rs2.getString(1));
            }
        }
    }
}
