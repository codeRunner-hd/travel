package com.hand.travel.dao;

import com.hand.travel.domain.Category;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/28 21:02
 */
public interface CategoryDao {
    /**
     * 查询商品目录
     *
     * @return
     */
    List<Category> findAll();

    /**
     * 根据目录查询
     * @param cname
     * @return
     */
    Category findByName(String cname);
}
