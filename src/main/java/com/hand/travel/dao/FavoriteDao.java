package com.hand.travel.dao;

import com.hand.travel.domain.Route;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/5/1 10:04
 */
public interface FavoriteDao {
    /**
     * 根据线路rid和用户uid判断是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(int rid, int uid);

    /**
     * 根据线路rid查询收藏次数
     *
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 根据线路rid和用户uid进行添加收藏操作
     *
     * @param rid
     * @param uid
     */
    void addFavorite(String rid, int uid);

    /**
     * 根据收藏次数查询前4条数据
     *
     * @return
     */
    List<Route> findRids();

    /**
     * 根据rid查询route对象
     *
     * @param rid
     * @return
     */
    Route findByRid(int rid);
}
