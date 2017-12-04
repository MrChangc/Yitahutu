package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\11\11 0011.
 */
public class RefundRecordModel extends SugarRecord {

    /**
     * address : 山西长治紫金大厦
     * createTime : 1507860363672
     * freight : 0
     * goodsId : 3
     * id : 1
     * introduce : 这是九牛寨好奶这是九牛寨好奶这是九牛寨好奶这是九牛寨好奶
     * name : 酸奶1
     * num : 6
     * orderNumber : 102369874210
     * originalPrice : 6.5
     * presentPrice : 3.5
     * totalPrice : 3.5
     * region : 山西长治
     * state : 6
     * url : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=955557085,3033417684&fm=27&gp=0.jpg
     * userId : 2
     * userName : 王五
     * userPhone : 123456789
     * zipCode : 47300
     */

    private String address;
    private long createTime;
    private int freight;
    private int goodsId;
    private String introduce;
    private String name;
    private int num;
    private String orderNumber;
    private double originalPrice;
    private double presentPrice;
    private double totalPrice;
    private String region;
    private int state;
    private String url;
    private int userId;
    private String userName;
    private String userPhone;
    private int zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(double presentPrice) {
        this.presentPrice = presentPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
