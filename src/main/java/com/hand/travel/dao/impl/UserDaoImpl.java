package com.hand.travel.dao.impl;

import com.hand.travel.dao.UserDao;
import com.hand.travel.domain.User;
import com.hand.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/26 15:58
 */
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

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
            e.printStackTrace();
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
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?)";
        // 2、执行sql
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据激活码查找用户
     *
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            // 定义sql
            String sql = "select * from tab_user where code = ?";
            // 执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 更新激活状态信息
     *
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        // 此处捕获一下异常，避免用户用户没有查询到或用户信息封装失败报异常
        try {
            // 1、定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            // 2、执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e) {

        }
        return user;
    }
}
