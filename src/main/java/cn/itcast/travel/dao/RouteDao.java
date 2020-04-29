package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:21
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     *
     * @param cid
     * @return
     */
    public int findTotalCount(int cid);

    /**
     * 根据cid,start,pageSize查询当前页面的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize);
}
