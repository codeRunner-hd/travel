package cn.itcast.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游线路商品实体类
 *
 * @author Admin
 */
public class Route implements Serializable {

    /**
     * 线路id，必输
     */
    private int routeId;
    /**
     * 线路名称，必输
     */
    private String routeName;
    /**
     * 价格，必输
     */
    private double price;
    /**
     * 线路介绍
     */
    private String routeIntroduce;
    /**
     * 是否上架，必输，0代表没有上架，1代表是上架
     */
    private String routeFlag;
    /**
     * 上架时间
     */
    private String routeDate;
    /**
     * 是否主题旅游，必输，0代表不是，1代表是
     */
    private String isThemeTour;
    /**
     * 收藏数量
     */
    private int count;
    /**
     * 所属分类，必输
     */
    private int categoryId;
    /**
     * 缩略图
     */
    private String routeImage;
    /**
     * 所属商家
     */
    private int saleId;
    /**
     * 抓取数据的来源id
     */
    private String sourceId;

    /**
     * 所属分类
     */
    private Category category;
    /**
     * 所属商家
     */
    private Seller seller;
    /**
     * 商品详情图片列表
     */
    private List<RouteImg> routeImgList;

    /**
     * 无参构造方法
     */
    public Route() {
    }

    /**
     * 有参构造方法
     *
     * @param routeId
     * @param routeName
     * @param price
     * @param routeIntroduce
     * @param routeFlag
     * @param routeDate
     * @param isThemeTour
     * @param count
     * @param categoryId
     * @param routeImage
     * @param saleId
     * @param sourceId
     */
    public Route(int routeId, String routeName, double price, String routeIntroduce, String routeFlag, String routeDate, String isThemeTour, int count, int categoryId, String routeImage, int saleId, String sourceId) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.routeFlag = routeFlag;
        this.routeDate = routeDate;
        this.isThemeTour = isThemeTour;
        this.count = count;
        this.categoryId = categoryId;
        this.routeImage = routeImage;
        this.saleId = saleId;
        this.sourceId = sourceId;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRouteFlag() {
        return routeFlag;
    }

    public void setRouteFlag(String routeFlag) {
        this.routeFlag = routeFlag;
    }

    public String getRouteDate() {
        return routeDate;
    }

    public void setRouteDate(String routeDate) {
        this.routeDate = routeDate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRouteImage() {
        return routeImage;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
}
