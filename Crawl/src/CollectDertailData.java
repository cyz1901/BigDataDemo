import org.junit.jupiter.api.Test;

import java.sql.*;

public class CollectDertailData {
    Connection connection;
    ResultSet resultSet;
    Statement statement;
    String selString = "SELECT * FROM dataurl";
    @Test
    public void printtest() throws SQLException {
        connection = JDBCMysqlUtils.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(selString);
        while (resultSet.next()){
            System.out.println(resultSet.getString("url"));
        }
        statement.close();
        connection.close();
    }
}
