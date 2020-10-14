package com.hand.travel.dao;

import com.hand.travel.domain.User;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/26 15:55
 */
public interface UserDao {
    /**
     * 根据用户名查询用户对象
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     *
     * @param user
     */
    public void save(User user);

    /**
     * 根据激活码查找用户
     *
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新激活状态信息
     *
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
