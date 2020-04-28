package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/28 23:58
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    /**
     * 线路分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        // 2.处理参数
        int cid,currentPage,pageSize = 0;
        // 若商品类别编号不为空，则进行强转
        if (cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }else{
            cid = 0;
        }
        // 若当前页码不为空，则进行强转，否则设置当前页码默认为1
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        // 若页码条数不为空，则进行强转，否则设置页码条数默认为10条
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 10;
        }
        // 3.调用service方法
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize);
        // 4.将对象序列化为json，返回
        writeValue(pageBean,response);
    }
}
