package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * 商品目录
 *
 * @Author: Admin-han
 * @DateTime: 2020/4/28 21:11
 */
public interface CategoryService {

    /**
     * 查询商品目录
     *
     * @return
     */
    public List<Category> findAll();
}
