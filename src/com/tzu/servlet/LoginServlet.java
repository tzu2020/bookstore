package com.tzu.servlet;

import com.tzu.bean.User;
import com.tzu.service.UserService;
import com.tzu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tzu
 * @version 1.0
 * @date 2020/9/7 19:27
 */
public class LoginServlet extends HttpServlet {
    private UserService  userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理编码
        request.setCharacterEncoding("UTF-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用service层登录方法
        User user = userService.login(username, password);
        //判断
        if (user == null){
            //登录失败：转发
            request.getRequestDispatcher("/pages/user/login.html").forward(request,response);
        }else {
            //登录成功：重定向
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
        }
    }
}
