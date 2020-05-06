
import java.sql.*;

public class JDBCMysqlUtils {
    private static final String connectionURL = "jdbc:mysql://localhost:3306/bigdata?useSSL=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "123456";

    //创建数据库的连接

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动链接成功");
            return   DriverManager.getConnection(connectionURL,username,password);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库的连接
    public static void close(ResultSet rs, Statement stmt, Connection con) throws SQLException {
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }

    public static void main(String[] args) {
        Connection connection = JDBCMysqlUtils.getConnection();
    }
}
