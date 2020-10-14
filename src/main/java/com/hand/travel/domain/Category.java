package com.hand.travel.domain;

import java.io.Serializable;

/**
 * 分类实体类
 * @author Admin
 */
public class Category implements Serializable {

    /**
     * 分类id
     */
    private int cid;
    /**
     * 分类名称
     */
    private String cname;

    public Category() {
    }

    public Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}