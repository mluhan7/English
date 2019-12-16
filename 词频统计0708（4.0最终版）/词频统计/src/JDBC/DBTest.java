package JDBC;

import java.sql.*;

/**
 * @author Dgd
 * @create 2017-10-16-21:25
 * 测试数据库
 */
public class DBTest {
    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    //数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/english?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "tiantian";

    public static boolean SortWords(String words,String vocabulary){
        boolean flag = true;
        Connection connection = null;
        String sql = "";
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            //mysql查询语句
            if(vocabulary == "cet4") {
                sql = "SELECT word FROM cet4 where word = ?";
                //System.out.println(sql);
            }
            if(vocabulary == "cet6"){
                sql = "SELECT word FROM cet6 where word = ?";
                //System.out.println(sql);
            }
            /*
            Statement s = connection.createStatement();
            s.execute("create index word_index on cet4(word)");*/
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1,words);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                System.out.println("word:" + rs.getString("word"));
                flag = false;
            }
            rs.close();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(flag) {
            System.out.println(flag);
        }
        return flag;
    }
}
