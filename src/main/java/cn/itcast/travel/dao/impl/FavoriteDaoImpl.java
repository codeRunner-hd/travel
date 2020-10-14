package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/5/1 10:05
 */
public class FavoriteDaoImpl implements FavoriteDao {
    DataSource dataSource;
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据线路rid和用户uid判断是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite != null;
    }

    /**
     * 根据线路rid查询收藏次数
     *
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 根据线路rid和用户uid进行添加收藏操作
     *
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(String rid, int uid) {
        String sql = "insert into tab_favorite values (?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }

    /**
     * 根据收藏次数查询前4条数据
     *
     * @return
     */
    @Override
    public List<Route> findRids() {
        String sql = "select rid,count(*) from tab_favorite group by rid order by count(*) desc limit 0 , 4";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     * 根据rid查询route对象
     *
     * @param rid
     * @return
     */
    @Override
    public Route findByRid(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
}
