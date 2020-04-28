package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:24
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据cid查询总记录数
     *
     * @param cid
     */
    @Override
    public int findTotalCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        return template.queryForObject(sql, Integer.class, cid);
    }

    /**
     * 根据cid,start,pageSize查询当前页面的数据集合
     * @param cid
     * @param start
     * @param pageSize
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";
        return template.query(sql, new BeanPropertyRowMapper<Route>(),cid,start,pageSize);
    }
}
