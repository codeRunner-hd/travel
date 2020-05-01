package cn.itcast.travel.service;

/**
 * @Author: Admin-han
 * @DateTime: 2020/5/1 10:02
 */
public interface FavoriteService {
    /**
     * 根据线路rid和用户uid判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid, int uid);

    /**
     * 根据线路rid和用户uid进行添加收藏操作
     * @param rid
     * @param uid
     */
    public void addFavorite(String rid, int uid);
}
