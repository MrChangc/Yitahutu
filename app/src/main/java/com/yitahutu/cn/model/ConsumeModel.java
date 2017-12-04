package com.yitahutu.cn.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Administrator on 2017\11\13 0013.
 */
public class ConsumeModel extends SugarRecord implements Serializable{

    /**
     * creatTime : 1510219422735
     * id : 2
     * money : 87
     * paymentMethod : 牛币付款
     * paymentNum : 859874663456
     * record : 购买商品
     * state : 2
     * succeed : 0
     * userId : 2
     */

    private long creatTime;
    private int money;
    private String paymentMethod;
    private String paymentNum;
    private String record;
    private int state;
    private int succeed;
    private int userId;

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
