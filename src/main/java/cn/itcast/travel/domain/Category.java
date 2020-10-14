package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 分类实体类
 * @author Admin
 */
public class Category implements Serializable {

    /**
     * 分类id
     */
    private int categoryId;
    /**
     * 分类名称
     */
    private String categoryName;

    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + categoryId +
                ", cname='" + categoryName + '\'' +
                '}';
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}