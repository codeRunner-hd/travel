package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:12
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();

    /**
     * 线路分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        PageBean<Route> pageBean = new PageBean<>();
        // 设置当前页码
        pageBean.setCurrentPage(currentPage);
        // 设置页码条数
        pageBean.setPageSize(pageSize);

        // 获取总记录数，并设置
        int totalCount = routeDao.findTotalCount(cid);
        pageBean.setTotalCount(totalCount);
        // 获取页面起始条数
        int start = (currentPage - 1) * pageSize;
        // 获取页面数据集合
        List<Route> list = routeDao.findByPage(cid, start, pageSize);

        // 获取总页码，并设置
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
