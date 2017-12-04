package com.yitahutu.cn.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
public class FinanceDetailModel extends SugarRecord implements Serializable{

    /**
     * aggregate : 2000
     * alreadySum : 50
     * createTime : 1507775985368
     * cycle : 6
     * expireTime : 1507775985368
     * headline : 奶牛收益100%
     * id : 1
     * nummber : 5
     * price : 2000
     * rate : 0.2
     * sketch : 奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍
     * sum : 100
     * type : 0
     * url : 0
     */

    private int aggregate;
    private int alreadySum;
    private long createTime;
    private int cycle;
    private long expireTime;
    private String headline;
    private int nummber;
    private int price;
    private double rate;
    private String sketch;
    private int sum;
    private int type;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAggregate() {
        return aggregate;
    }

    public void setAggregate(int aggregate) {
        this.aggregate = aggregate;
    }

    public int getAlreadySum() {
        return alreadySum;
    }

    public void setAlreadySum(int alreadySum) {
        this.alreadySum = alreadySum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public int getNummber() {
        return nummber;
    }

    public void setNummber(int nummber) {
        this.nummber = nummber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
