package com.tzu.dao.impl;

import com.tzu.bean.User;
import com.tzu.dao.JDBCBaseDao;
import com.tzu.dao.UserDao;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 16:49
 */
public class UserDaoImpl implements UserDao {
    private JDBCBaseDao<User> jdbcBaseDao = new JDBCBaseDao<>();

    @Override
    public User login(String username, String password) {
        //从数据库中查询用户名和密码，用户名和密码都匹对上则返回一个有值的对象，否则返回一个空对象
        String sql = "select * from bs_user where username=? and password=?";
        User user = jdbcBaseDao.getBean(User.class, sql, username, password);

        return user;
    }

    @Override
    public User checkUsername(String username) {
        String sql = "select * from bs_user where username=?";
        User user = jdbcBaseDao.getBean(User.class, sql, username);

        return user;
    }

    @Override
    public int register(User user) {
        String sql = "insert into bs_user values(null,?,?,?)";
        int result = jdbcBaseDao.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return result;
    }
}
