package com.tzu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 18:33
 */
public class JDBCUtils {
    private static DataSource dataSource;

    static {
        try {
            //创建Properties对象，用来读取jdbc.properties文件中的内容
            Properties properties = new Properties();
            //通过类加载器获取jdbc.properties文件的字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //将jdbc.properties文件中的内容加载到properties对象的内存中
            //jdbc.properties文件中的键必须和数据源中表示相关信息的属性名保持一致
            properties.load(is);
            //通过properties对象内存中提供的信息创建数据源
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过数据库连接池（durid）获取连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            //在数据库连接池里拿到一个连接对象
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放连接：把连接对象还给数据库连接池（不是关闭连接）
     * @param connection
     */
    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
