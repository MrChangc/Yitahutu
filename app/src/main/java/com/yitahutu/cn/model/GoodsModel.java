package com.yitahutu.cn.model;

import com.orm.SugarRecord;
import com.yitahutu.cn.Utils.ConstantUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017\10\16 0016.
 */
public class GoodsModel extends SugarRecord implements Serializable {

    /**
     * coverUrl : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559475459,32662131&fm=27&gp=0.jpg
     * createTime : 1507860363672
     * freight : 0
     * id : 3
     * introduce : 这是九牛寨好奶这是九牛寨好奶这是九牛寨好奶这是九牛寨好奶
     * name : 酸奶1
     * originalPrice : 6.5
     * presentPrice : 3.5
     * recommendId : 1
     * top : 1
     * typeId : 1
     * url : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=955557085,3033417684&fm=27&gp=0.jpg
     * monthSales : null
     */

    private String coverUrl;
    private long createTime;
    private int freight;
    private String introduce;
    private String name;
    private double originalPrice;
    private double presentPrice;
    private int recommendId;
    private int top;
    private int typeId;
    private String url;
    private String monthSales;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public int getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(int recommendId) {
        this.recommendId = recommendId;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(String monthSales) {
        this.monthSales = monthSales;
    }

}
