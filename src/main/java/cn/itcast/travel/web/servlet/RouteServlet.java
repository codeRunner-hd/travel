package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
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
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 线路分页查询
     *
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

        // 接受搜索框名称参数rname
        String rname = request.getParameter("rname");

        if (rname != null && rname.length() > 0) {
            // 处理rname乱码的问题
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }

        // 2.处理参数
        int cid, currentPage, pageSize = 0;
        // 若商品类别编号不为空，则进行强转
        if (cidStr != null && cidStr.length() > 0 && !"".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        } else {
            cid = 0;
        }
        // 若当前页码不为空，则进行强转，否则设置当前页码默认为1
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        // 若页码条数不为空，则进行强转，否则设置页码条数默认为10条
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 10;
        }
        // 3.调用service方法，查询pageBean对象
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        // 4.将pageBean对象序列化为json，返回
        writeValue(pageBean, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收rid
        String rid = request.getParameter("rid");
        // 2.调用serivce方法查询route对象
        Route route = routeService.findOne(rid);
        // 3.将route对象转换为json，写回客户端
        writeValue(route,response);
    }

    /**
     * 判断当前登录用户是否收藏过该路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收rid
        String rid = request.getParameter("rid");
        // 2.从session中获取user对象
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            // 用户已登录
            uid = 0;
        }else{
            // 用户未登录
            uid = user.getUid();
        }

        // 3.调用serivce方法查询用户是否收藏该旅游线路
        boolean flag = favoriteService.isFavorite(rid, uid);

        // 3.将对象转换为json，写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏操
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收rid
        String rid = request.getParameter("rid");
        // 2.从session中获取user对象
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            // 用户已登录
            return;
        }else{
            // 用户未登录
            uid = user.getUid();
        }

        // 3.调用serivce方法添加收藏
        favoriteService.addFavorite(rid, uid);
    }
}