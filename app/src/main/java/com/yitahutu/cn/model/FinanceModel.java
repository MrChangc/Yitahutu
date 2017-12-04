package com.yitahutu.cn.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class FinanceModel extends SugarRecord implements Serializable{


    /**
     * alreadySum : 50
     * cycle : 6
     * headline : 奶牛收益100%
     * id : 1
     * price : 2000
     * rate : 0.2
     * sketch : 奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍奶牛介绍
     * sum : 100
     *
     */

    private int alreadySum;
    private int cycle;
    private String headline;
    private int price;
    private double rate;
    private String sketch;
    private int sum;
    private String url;

    public int getAlreadySum() {
        return alreadySum;
    }

    public void setAlreadySum(int alreadySum) {
        this.alreadySum = alreadySum;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
