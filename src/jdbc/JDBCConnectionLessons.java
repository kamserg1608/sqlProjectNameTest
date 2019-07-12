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
            System.out.println("We're connected!!!!");
            statement.executeUpdate(" DROP TABLE IF EXISTS Users");
            statement.executeUpdate("CREATE TABLE Users (id SERIAL, name VARCHAR(30) NOT NULL , password VARCHAR(30) NOT NULL, PRIMARY KEY (id) )");
            statement.executeUpdate("INSERT INTO Users (name, password) VALUES ('max', '123')");
            statement.executeUpdate("INSERT INTO Users (name, password) VALUES ('otherGuy', '312')");
//            ResultSet resultSet = statement.executeQuery("SELECT  * FROM Books");


            //region data for request
//            String userID = "1";
//            sql injection
//            https://www.w3schools.com/sql/sql_injection.asp
            String userID = "1' or 1 = '1";
            //endregion

/*        //region sql injection
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE id = '" + userID + " ' ");
            while (resultSet.next()){
                System.out.println("userName: " + resultSet.getString("name"));
                System.out.println("userPassword: " + resultSet.getString("password"));
            }
        //endregion*/

//            System.out.println("-------------------------");


            // region defend of sql injection
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ? and name = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
//            preparedStatement.setString(2,"userName");
            preparedStatement.setString(1, userID);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()) {
                System.out.println("userName: " + resultSet1.getString("name"));
                System.out.println("userPassword: " + resultSet1.getString("password"));
            }
            //endregion
        }
    }
}


