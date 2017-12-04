package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\11\27 0027.
 */
public class TotalModel extends SugarRecord{

    /**
     * totalMoney : 115622
     * wallet : 1200
     * aggregate : 87
     * dividend : 1200
     * yesteday : 1200
     */

    private float totalMoney;
    private float wallet;
    private float aggregate;
    private float dividend;
    private float yesteday;

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public float getAggregate() {
        return aggregate;
    }

    public void setAggregate(float aggregate) {
        this.aggregate = aggregate;
    }

    public float getDividend() {
        return dividend;
    }

    public void setDividend(float dividend) {
        this.dividend = dividend;
    }

    public float getYesteday() {
        return yesteday;
    }

    public void setYesteday(float yesteday) {
        this.yesteday = yesteday;
    }
}
