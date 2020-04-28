package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 线路
 *
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:09
 */
public interface RouteService {

    /**
     * 线路分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize);
}
