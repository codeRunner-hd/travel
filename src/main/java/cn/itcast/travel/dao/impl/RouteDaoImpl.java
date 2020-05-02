package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public int findTotalCount(int cid, String rname) {
        // String sql = "select count(*) from tab_route where cid = ?";
        // 1.定义一个sql模板
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        // 创建集合用来存放条件属性值
        List params = new ArrayList<>();
        // 判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            // 往集合中添加？对应的属性值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");

            params.add("%" + rname + "%");
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid,start,pageSize查询当前页面的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        // 1.定义一个sql模板
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        // 创建集合用来存放条件属性值
        List params = new ArrayList<>();
        // 判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            // 往集合中添加？对应的属性值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ? ");

            params.add("%" + rname + "%");
        }
        // 分页条件
        sb.append(" limit ? , ?");

        params.add(start);
        params.add(pageSize);

        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /**
     * 根据id查询一个route对象
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    /**
     * 查询最新旅游路线
     *
     * @return
     */
    @Override
    public List<Route> findByRdate() {
        String sql = "select rid,rdate from tab_route order by rdate desc limit 0 , 4";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     * 查询主题旅游路线
     *
     * @return
     */
    @Override
    public List<Route> findByTheme() {
        String sql = "SELECT * FROM tab_route WHERE isThemeTour = '1' ORDER BY rdate DESC LIMIT 0 , 4";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
    }
}
