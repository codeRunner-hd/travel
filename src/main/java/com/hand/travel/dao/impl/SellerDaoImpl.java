package com.hand.travel.dao.impl;

import com.hand.travel.dao.SellerDao;
import com.hand.travel.domain.Seller;
import com.hand.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/30 17:34
 */
public class SellerDaoImpl implements SellerDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据route对象中的sid查询商家信息
     *
     * @param sid
     * @return
     */
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
    }
}
