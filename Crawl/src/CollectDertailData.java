import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;

public class CollectDertailData{
    org.jsoup.Connection con;
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
            con = Jsoup.connect(resultSet.getString("url"));


        }
        statement.close();
        connection.close();
    }


    @Test
    public void cattest() throws IOException {
        con = Jsoup.connect("https://hangzhou.anjuke.com/community/trends/160920");
        Document document = con.get();
        Elements link = document.body().getElementsByClass("avg");
        System.out.println(link);
    }


}


