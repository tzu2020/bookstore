package com.tzu.service.impl;

import com.tzu.bean.User;
import com.tzu.dao.UserDao;
import com.tzu.dao.impl.UserDaoImpl;
import com.tzu.service.UserService;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 16:50
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.login(username, password);
        return user;
    }

    @Override
    public boolean register(User user) {
        int result = userDao.register(user);
        //判断
        if (result > 0){
            return true;
        }
        return false;
    }
}
