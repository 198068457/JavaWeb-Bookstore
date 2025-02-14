package com.atguigu.myssm.basedao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    //private static ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
    //private static ThreadLocal<Object> threadLocal3 = new ThreadLocal<>();

    public static  String DRIVER = "com.mysql.jdbc.Driver";
    public static  String URL = "jdbc:mysql://127.0.0.1:3306/bookdb?useUnicode=true";
    public static  String USER = "root";
    public static  String PWD = "20010678hong";

//    static{
//        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(is);
//            DRIVER = properties.getProperty("jdbc.driver");
//            URL = properties.getProperty("jdbc.url");
//            USER = properties.getProperty("jdbc.user");
//            PWD = properties.getProperty("jdbc.pwd");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn==null){
            conn =createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get() ;
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn==null){
            return ;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
