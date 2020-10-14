package com.hand.travel.service.impl;

import com.hand.travel.dao.UserDao;
import com.hand.travel.dao.impl.UserDaoImpl;
import com.hand.travel.domain.User;
import com.hand.travel.service.UserService;
import com.hand.travel.util.MailUtils;
import com.hand.travel.util.UuidUtil;

/**
 * @author Admin-han
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        // 1、根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if (u != null) {
            // 用户名存在，保存失败
            return false;
        }
        // 2、保存用户信息
        // 2.1、设置激活码，唯一的一个字符串
        user.setCode(UuidUtil.getUuid());
        // 2.2、设置激活状态
        user.setStatus("N");
        userDao.save(user);

        // 3、激活邮件发送，设置邮件正文
        String content = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null) {
            // 调用dao的修改激活状态方法
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}