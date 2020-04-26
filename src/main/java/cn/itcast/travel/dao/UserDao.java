package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/26 15:55
 */
public interface UserDao {
    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    public void save(User user);
}
