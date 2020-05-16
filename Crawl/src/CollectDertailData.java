import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CollectDertailData{
    org.jsoup.Connection con;
    Connection connection;
    ResultSet resultSet_one;
    PreparedStatement resultSet_two;
    Statement statement;
    String selString = "SELECT * FROM dataurl";
    String setString = "INSERT INTO deatailvillage (地址,建造年代,绿化率,物业费,总面积) VALUES (?,?,?,?,?)";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    Document document_find;
    Elements link_find;




    @Test
    public void printtest() throws SQLException, InterruptedException, IOException, ParseException {
        connection = JDBCMysqlUtils.getConnection();
        statement = connection.createStatement();
        resultSet_one = statement.executeQuery(selString);
        resultSet_two = connection.prepareStatement(setString);
        while (resultSet_one.next()){
            System.out.println(resultSet_one.getString("url"));
            con = Jsoup.connect(resultSet_one.getString("url"));
            document_find = con.get();
            link_find = document_find.body().select("dd");
            resultSet_two.setString(1,
                    document_find.select(".sub-hd").text());
            try {
                resultSet_two.setString(2,
                        formatter.parse(Pattern.compile("[\\u4e00-\\u9fa5]").matcher(link_find.eq(8).text()).replaceAll("")).toString());
            }catch (ParseException e){
                resultSet_two.setString(2,
                        null);
            }
            try{
                resultSet_two.setString(3,
                        String.valueOf(Double.parseDouble(Pattern.compile("%\\(.+").matcher(link_find.eq(11).text()).replaceAll(""))/100));
            }catch (NumberFormatException e){
                resultSet_two.setString(3,
                        null);
            }
            resultSet_two.setString(4,
                    Pattern.compile("[\\u4e00-\\u9fa5/㎡]").matcher(link_find.eq(5).text()).replaceAll(""));
            resultSet_two.setString(5,
                    Pattern.compile("m²").matcher(link_find.eq(6).text()).replaceAll(""));
            System.out.println("success!");
            Thread.currentThread().sleep(200);


        }
        resultSet_two.close();
        statement.close();
        connection.close();
    }


    @Test
    public void cattest() throws IOException, ParseException {
        con = Jsoup.connect("https://hangzhou.anjuke.com/community/view/746818");
        Document document = con.get();
        Elements link = document.body().select("dd");
        //System.out.println(document.select(".map-link").attr("title"));//name=名字
        //System.out.println(document.select(".sub-hd").text());//地址
        //System.out.println(formatter.parse(Pattern.compile("[\\u4e00-\\u9fa5]").matcher(link.eq(8).text()).replaceAll("")));//建造年代 link.eq(8).text()
        //System.out.println(Double.valueOf(Pattern.compile("%\\(.+").matcher(link.eq(11).text()).replaceAll(""))/100);//绿化率 link.eq(11).text()
        System.out.println(Pattern.compile("[\\u4e00-\\u9fa5/㎡]").matcher(link.eq(5).text()).replaceAll(""));//物业费
        System.out.println(link);
        //System.out.println(Pattern.compile("m²").matcher(link.eq(6).text()).replaceAll(""));//总面积

        }


}


