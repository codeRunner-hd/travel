package com.hand.travel.domain;

import java.io.Serializable;

/**
 * 收藏实体类
 *
 * @author Admin
 */
public class Favorite implements Serializable {
    /**
     * 旅游线路对象
     */
    private Route route;
    /**
     * 收藏时间
     */
    private String date;
    /**
     * 所属用户
     */
    private User user;

    /**
     * 无参构造方法
     */
    public Favorite() {
    }

    /**
     * 有参构造方法
     *
     * @param route
     * @param date
     * @param user
     */
    public Favorite(Route route, String date, User user) {
        this.route = route;
        this.date = date;
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}