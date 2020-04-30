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
     * 根据类别进行线路分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询一个route对象
     * @param rid
     * @return
     */
    public Route findOne(String rid);
}
