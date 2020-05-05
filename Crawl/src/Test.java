import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Test {
    Elements pTable;
    Document document;
    FileWriter fileWriter;
    Connection con;
    String testURL;
    public void getGrade() throws IOException, InterruptedException {
        for (int i = 1 ; i<51; i++) {

            testURL = "https://hangzhou.anjuke.com/community//p"+i+"/";//li-itemmod
            con = Jsoup.connect(testURL);
            document = con.get();
            pTable = document.body().getElementsByClass("li-itemmod");
            fileWriter = new FileWriter("Crawl/resources/link.txt",true);
            for (Element p : pTable){
                fileWriter.write(p.attr("link")+"\r\n");
                System.out.println(p.attr("link"));

            }
            Thread.currentThread().sleep(2000);
            System.out.println(i);
            fileWriter.close();
        }


        //System.out.println(pTable.toString());


    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Test test = new Test();
        test.getGrade();
    }
}