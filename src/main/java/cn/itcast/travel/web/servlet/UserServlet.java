package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/27 22:15
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private final UserService userService = new UserServiceImpl();

    /**
     * 注册功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        // 首先校验验证码是否正确，如果不正确就不往下进行了
        String check = request.getParameter("check");

        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkCodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        // 校验验证码之后立马进行从session中清楚验证码，保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(check)) {
            // 验证码错误
            // 提示注册失败
            info.setFlag(false);
            info.setErrorMsg("对不起，验证码输入错误！");

            // 将info对象序列化为json,并返回
            String json = writeValueAsString(info, response);
            response.getWriter().write(json);

            // 直接返回，不进行接下来的操作
            return;
        }

        // 1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        // 2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3、调用service完成注册
        //UserService userService = new UserServiceImpl();
        boolean flag = userService.register(user);
        // 4、响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("对不起，用户已存在！");
        }

        // 将info对象序列化为json,并返回
        String json = writeValueAsString(info, response);
        response.getWriter().write(json);
    }

    /**
     * 登录功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("check");
        if (username == null || "".equals(username)) {
            info.setErrorMsg("用户名不能为空");
        } else if (password == null || "".equals(password)) {
            info.setErrorMsg("密码不能为空");
        } else if (check == null || "".equals(check)) {
            info.setErrorMsg("验证码不能为空");
        } else {
            // 调用验证码方法，如果不正确就不往下进行了
            checkCode(info, check, request, response);

            // 1、获取用户名和密码数据
            Map<String, String[]> map = request.getParameterMap();
            // 2、封装User对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            // 3、调用service登录方法
            User u = userService.login(user);

            // 4、判断用户对象是否为null
            if (u == null) {
                // 用户名或密码错误
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误！");
            }
            // 5、判断用户是否激活
            if (u != null && !"Y".equals(u.getStatus())) {
                // 用户尚未激活
                info.setFlag(false);
                info.setErrorMsg("用户尚未激活，请激活");
            }
            // 6、判断用户登录成功
            if (u != null && "Y".equals(u.getStatus())) {
                // 登录成功
                info.setFlag(true);

                // 将查询到的用户存到session中，便于读取用户名，展示到首页
                request.getSession().setAttribute("user", u);
            }
        }

        // 将info序列化为json，并且写回客户端
        writeValue(info, response);
    }

    /**
     * 查询单个用户功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        // 将user序列化为json，并且写回客户端
        writeValue(user, response);
    }

    /**
     * 退出功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、销毁session
        request.getSession().invalidate();
        // 2、跳转登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            // 2、调用service激活方法
            //UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            // 3、判断标记
            String msg = "";
            if (flag) {
                // 激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                // 激活失败
                msg = "激活失败，请检查网络或联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}