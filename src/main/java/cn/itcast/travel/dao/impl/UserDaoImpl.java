package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/26 15:58
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        // 此处捕获一下异常，避免用户用户没有查询到或用户信息封装失败报异常
        try {
            // 1、定义sql
            String sql = "select * from tab_user where username = ?";
            // 2、执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    @Override
    public void save(User user) {
        // 1、定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values (?,?,?,?,?,?,?)";
        // 2、执行sql
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }
}
