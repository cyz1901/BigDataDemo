import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {
    Elements pTable;
    Document document;

    public void getGrade() throws IOException {
        String testURL = "https://hangzhou.anjuke.com/community//p1/";//li-itemmod
        Connection con = Jsoup.connect(testURL);
        document = con.get();
        pTable = document.body().getElementsByClass("li-itemmod");

        for (Element p : pTable){
            System.out.println(p.attr("link"));
        }


        //System.out.println(pTable.toString());


    }


    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.getGrade();
    }
}