package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:12
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 根据类别进行线路分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pageBean = new PageBean<>();
        // 设置当前页码
        pageBean.setCurrentPage(currentPage);
        // 设置页码条数
        pageBean.setPageSize(pageSize);

        // 获取总记录数，并设置
        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);
        // 获取页面起始条数
        int start = (currentPage - 1) * pageSize;
        // 获取页面数据集合,并设置
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);

        // 获取总页码，并设置
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    /**
     * 根据id查询一个route对象
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        // 1.首先根据rid查询一个route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        // 2.根据route对象中的id查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRouteId());
        route.setRouteImgList(routeImgList);
        // 3.根据route对象中的sid查询商家详细信息
        Seller seller = sellerDao.findBySid(route.getSaleId());
        route.setSeller(seller);

        // 4.查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRouteId());
        route.setCount(count);

        return route;
    }

    /**
     * 根据收藏次数查询前4条数据的rid
     *
     * @return
     */
    @Override
    public List<Route> popularTravel() {
        List<Route> fav_list = favoriteDao.findRids();
        List<Route> routes_list = new ArrayList<>();
        for (int i = 0; i < fav_list.size(); i++) {
            Route route = favoriteDao.findByRid(fav_list.get(i).getRouteId());
            routes_list.add(route);
        }
        return routes_list;
    }

    /**
     * 查询最新旅游的前4条数据
     *
     * @return
     */
    @Override
    public List<Route> newestTravel() {
        List<Route> routes = routeDao.findRouteByDate();
        List<Route> routes_list = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            Route route = favoriteDao.findByRid(routes.get(i).getRouteId());
            routes_list.add(route);
        }
        return routes_list;
    }

    /**
     * 查询主题旅游的前4条数据
     *
     * @return
     */
    @Override
    public List<Route> themeTravel() {
        List<Route> routes = routeDao.findByTheme();
        List<Route> routes_list = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            Route route = favoriteDao.findByRid(routes.get(i).getRouteId());
            routes_list.add(route);
        }
        return routes_list;
    }

    /**
     * 国内游
     *
     * @return
     */
    @Override
    public List<Route> inTravel() {
        String cname = "国内游";
        Category category = categoryDao.findByName(cname);
        List<Route> routes = routeDao.findByCid(category.getCategoryId());
        List<Route> routes_list = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            Route route = favoriteDao.findByRid(routes.get(i).getRouteId());
            routes_list.add(route);
        }
        return routes_list;
    }

    /**
     * 国内游、境外游
     *
     * @return
     */
    @Override
    public List<Route> outTravel() {
        String cname = "出境游";
        Category category = categoryDao.findByName(cname);
        List<Route> routes = routeDao.findByCid(category.getCategoryId());
        List<Route> routesList = new ArrayList<>();
        for (Route value : routes) {
            Route route = favoriteDao.findByRid(value.getRouteId());
            routesList.add(route);
        }
        return routesList;
    }
}