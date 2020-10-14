package com.hand.travel.dao.impl;

import com.hand.travel.dao.CategoryDao;
import com.hand.travel.domain.Category;
import com.hand.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/28 21:06
 */
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询商品目录
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public Category findByName(String cname) {
        String sql = "select * from tab_category where cname = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cname);
    }
}
