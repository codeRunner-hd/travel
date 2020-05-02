package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

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
    public boolean isFavorite(int rid, int uid);

    /**
     * 根据线路rid查询收藏次数
     *
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * 根据线路rid和用户uid进行添加收藏操作
     * @param rid
     * @param uid
     */
    public void addFavorite(String rid, int uid);

    /**
     * 根据收藏次数查询前4条数据
     * @return
     */
    List<Route> findRids();

    /**
     * 根据rid查询route对象
     * @param rid
     * @return
     */
    Route findByRid(int rid);
}
