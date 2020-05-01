package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/27 22:08
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // 完成方法的分发
        // 1、获取请求路径
        String uri = req.getRequestURI();
        // 2、获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        try {
            // 3、获取方法对象Method，谁调用我，this就是谁
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 忽略访问修饰符，直接获取方法,但此方法不太好，实际场景下并不适用
            // Method declaredMethod = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 暴力反射
            // method.setAccessible(true);

            // 4、执行方法
            method.invoke(this, req, resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     *
     * @param object
     */
    public void writeValue(Object object, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), object);
    }

    /**
     * 将传入的对象序列化为json，返回给调用者
     *
     * @param object
     * @return
     */
    public String writeValueAsString(Object object, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        return mapper.writeValueAsString(object);
    }

    public void checkCode(ResultInfo info, String check, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
        // 校验验证码之后立马进行从session中清楚验证码，保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcodeServer == null || !checkcodeServer.equalsIgnoreCase(check)){
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
    }
}
