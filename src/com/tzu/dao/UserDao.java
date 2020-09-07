package com.tzu.dao;

import com.tzu.bean.User;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 16:49
 */
public interface UserDao {

    //登录
    User login(String username, String password);

    //查询用户是否已经存在
    User checkUsername(String username);

    //注册
    int register(User user);



}
