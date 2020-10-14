package com.hand.travel.service;

import com.hand.travel.domain.User;

/**
 * @author Admin-han
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user);
}
