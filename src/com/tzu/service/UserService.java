package com.tzu.service;

import com.tzu.bean.User;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 16:49
 */
public interface UserService {
    //登录
    User login(String username,String password);

    //注册
    boolean register(User user);
}
