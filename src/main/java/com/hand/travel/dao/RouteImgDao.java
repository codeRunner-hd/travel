package com.hand.travel.dao;

import com.hand.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/30 17:28
 */
public interface RouteImgDao {

    /**
     * 根据route对象中的id查询图片
     *
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
