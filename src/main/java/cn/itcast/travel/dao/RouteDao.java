package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;
import java.util.ListIterator;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:21
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     *
     * @param cid
     * @param rname
     * @return
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid,start,pageSize查询当前页面的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询一个route对象
     *
     * @param rid
     * @return
     */
    public Route findOne(int rid);

    /**
     * 查询最新旅游路线
     *
     * @return
     */
    public List<Route> findRouteByDate();

    /**
     * 查询主题旅游路线
     *
     * @return
     */
    public List<Route> findByTheme();

    /**
     * 根据cid查询路线信息
     *
     * @param cid
     * @return
     */
    public List<Route> findByCid(int cid);
}
