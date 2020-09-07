package com.tzu.dao;

import com.alibaba.druid.util.JdbcUtils;
import com.tzu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 18:47
 * @Description: 抽象增删改查功能的父类
 */
public class JDBCBaseDao<T> {

    //创建dbutils提供的操作数据库中数据的对象：QueryRunner
    private QueryRunner  queryRunner = new QueryRunner();

    /**
     * 实现增删改的功能
     * @param sql：要执行的sql语句
     * @param params：按照顺序为sql语句中的占位符所赋的值
     */
    public int update(String sql,Object...params){
        int result = 0;
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            result = queryRunner.update(connection,sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return result;
    }

    /**
     * 通过所设置的sql语句，查询一个list集合
     * @param clazz：提供sql语句查询出的数据所对应的实体类的Class对象
     * @param sql：要执行的sql语句
     * @param params：按照顺序为sql语句中的占位符所赋的值
     * @return
     * BeanListHandler：将sql语句查询的结果处理为一个list集合
     */
    public List<T> getBeanList(Class<T> clazz,String sql,Object...params){
        List<T> list = null;
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            list = queryRunner.query(connection,sql, new BeanListHandler<>(clazz),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return list;
    }

    /**
     * 通过所设置的sql语句，查询一个bean对象
     * @param clazz：提供sql语句查询出的数据所对应的实体类的Class对象
     * @param sql：要执行的sql语句
     * @param params：按照顺序为sql语句中的占位符所赋的值
     * @return
     * BeanHandler：将sql语句查询的结果处理为bean对象
     */
    public T getBean(Class<T> clazz,String sql,Object...params){
        T t = null;
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            t = queryRunner.query(connection,sql,new BeanHandler<>(clazz),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }
}
