package jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class JDBCConnectionLessons {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root";
        String connectionURl = "jdbc:mysql://localhost:3306/";
        forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionURl, userName, password)){
            System.out.println("We're connected!!!!");
        }
    }
}
