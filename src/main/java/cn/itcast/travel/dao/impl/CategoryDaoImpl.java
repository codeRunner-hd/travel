package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
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
