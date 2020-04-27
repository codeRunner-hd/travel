package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/27 9:41
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取激活码
        String code = request.getParameter("code");
        if (code != null){
            // 2、调用service激活方法
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            // 3、判断标记
            String msg = "";
            if (flag){
                // 激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                // 激活失败
                msg = "激活失败，请检查网络或联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
