package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author Admin-han
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

     /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        // 1、根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if (u != null){
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
        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null){
            // 调用dao的修改激活状态方法
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }
}