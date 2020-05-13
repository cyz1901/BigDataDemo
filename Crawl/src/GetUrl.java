import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GetUrl {
    Elements pTable;
    Document document;
    FileWriter fileWriter;
    Connection con;
    String testURL;
    java.sql.Connection connection = null;
    PreparedStatement preparedStatement = null;
    String urlString = "INSERT INTO dataurl (url,name,cost) VALUES (?,?,?)";


    @Test
    public void getGrade() throws IOException, InterruptedException, SQLException {

        for (int i = 1 ; i<51; i++) {

            testURL = "https://hangzhou.anjuke.com/community//p"+i+"/";//li-itemmod
            con = Jsoup.connect(testURL);
            document = con.get();
            pTable = document.body().getElementsByClass("li-itemmod");
            //System.out.println(pTable);
            //fileWriter = new FileWriter("Crawl/resources/link.txt",true);

            for (Element p : pTable){
                //fileWriter.write(p.attr("link")+"\r\n");
                //System.out.println(p.attr("link") + p.attr("alt"));
                System.out.println(p.select(".img").attr("alt")
                        +p.select("strong").text()
                );

            }


            Thread.currentThread().sleep(2000);
            System.out.println(i);


            //fileWriter.close();
        }

        //System.out.println(pTable.toString());
    }

    public void saveUrlToMysql() throws SQLException, IOException, InterruptedException {
        connection= JDBCMysqlUtils.getConnection();
        preparedStatement = connection.prepareStatement(urlString);

        for (int i = 1 ; i<51; i++) {

            testURL = "https://hangzhou.anjuke.com/community//p"+i+"/";//li-itemmod
            con = Jsoup.connect(testURL);
            document = con.get();
            pTable = document.body().getElementsByClass("li-itemmod");
            for (Element p : pTable){
                preparedStatement.setString(1,p.attr("link"));
                preparedStatement.setString(2,p.select(".img").attr("alt"));
                preparedStatement.setString(3,p.select("strong").text());
                preparedStatement.executeUpdate();
                System.out.println(p.attr("link"));

            }
            Thread.currentThread().sleep(2000);
            System.out.println(i);

        }
        preparedStatement.close();
        connection.close();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        GetUrl getUrl = new GetUrl();
        try {
            getUrl.saveUrlToMysql();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}