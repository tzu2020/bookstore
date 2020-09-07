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
 * @date 2020/9/7 20:22
 */
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理编码
        request.setCharacterEncoding("UTF-8");
        //获取请请求数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //封装数据
        User user = new User(null,username,password,email);
        //调用service的regist方法
        boolean result = userService.register(user);
        //判断
        if(result){
            //注册成功：重定向
            response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.html");
        }else {
            //注册失败：转发
            request.getRequestDispatcher("/pages/regist.html").forward(request,response);
        }

    }
}
