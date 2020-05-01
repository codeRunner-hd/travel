package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/28 21:12
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 查询商品目录
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        // 优化查询商品目录，采用redis缓存
        // 1.1、获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        // 1.2、使用sortedset进行排序查询
        // Set<String> categorys = jedis.zrange("category", 0, -1);
        // 1.3、查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;
        // 2、判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0) {
            // 若为空，则从数据库进行查询，然后存储到redis中
            // System.out.println("商品目录从数据库查询。。。");
            // 3.1、从数据库进行查询
            list = categoryDao.findAll();
            // 3.2、遍历集合，将集合数据存储到redis中 category的key中
            for (int i = 0; i < list.size(); i++) {
                jedis.zadd("category", list.get(i).getCid(), list.get(i).getCname());
            }
        } else {
            // 若集合不为空，则从redis中查询
            // System.out.println("商品目录从redis中查询。。。");
            // 将set集合中的数据存入到list集合中
            list = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                list.add(category);
            }
        }
        return list;
    }
}
