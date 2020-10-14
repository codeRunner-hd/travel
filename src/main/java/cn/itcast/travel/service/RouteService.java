package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

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
     * @param rname
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询一个route对象
     *
     * @param rid
     * @return
     */
    public Route findOne(String rid);

    /**
     * 查询人气旅游的前4条数据
     *
     * @return
     */
    public List<Route> popularTravel();

    /**
     * 查询最新旅游的前4条数据
     *
     * @return
     */
    public List<Route> newestTravel();

    /**
     * 查询主题旅游的前4条数据
     *
     * @return
     */
    public List<Route> themeTravel();

    /**
     * 国内游
     *
     * @return
     */
    public List<Route> inTravel();

    /**
     * 国内游、境外游
     *
     * @return
     */
    public List<Route> outTravel();
}
