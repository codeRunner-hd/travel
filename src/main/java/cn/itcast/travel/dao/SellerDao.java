package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/30 17:33
 */
public interface SellerDao {
    /**
     * 根据route对象中的sid查询商家信息
     *
     * @param sid
     * @return
     */
    public Seller findBySid(int sid);
}
