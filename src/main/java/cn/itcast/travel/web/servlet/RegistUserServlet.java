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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Admin-han
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 首先校验验证码是否正确，如果不正确就不往下进行了
        String check = request.getParameter("check");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        // 校验验证码之后立马进行从session中清楚验证码，保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcodeServer == null || !checkcodeServer.equalsIgnoreCase(check)){
            // 验证码错误
            ResultInfo info = new ResultInfo();
            // 提示注册失败
            info.setFlag(false);
            info.setErrorMsg("对不起，验证码输入错误！");

            // 将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            // 将json数据写回到客户端
            // 设置content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            // 直接返回，不进行接下来的操作
            return;
        }

        // 1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        // 2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3、调用service完成注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.regist(user);
        ResultInfo info = new ResultInfo();
        // 4、响应结果
        if (flag){
            // 注册成功
            info.setFlag(true);
        }else{
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("对不起，注册失败！");
        }

        // 将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        // 将json数据写回到客户端
        // 设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
