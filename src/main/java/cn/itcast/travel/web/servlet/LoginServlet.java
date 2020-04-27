package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/27 10:07
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        // 2、封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3、调用service登录方法
        UserService userService = new UserServiceImpl();
        User u = userService.login(user);

        ResultInfo info = new ResultInfo();
        // 4、判断用户对象是否为null
        if (u == null){
            // 用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        // 5、判断用户是否激活
        if (u != null && !"Y".equals(u.getStatus())){
            // 用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("用户尚未激活，请激活");
        }
        // 6、判断用户登录成功
        if (u != null && "Y".equals(u.getStatus())){
            // 登录成功
            info.setFlag(true);
        }
        // 将查询到的用户存到session中，便于读取用户名，展示到首页
        request.getSession().setAttribute("user",u);

        // 响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
