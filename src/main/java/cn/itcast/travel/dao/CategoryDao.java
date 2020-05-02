package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

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
    public List<Category> findAll();

    public Category findByName(String cname);
}
