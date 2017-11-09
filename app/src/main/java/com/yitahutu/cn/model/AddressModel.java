package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\10\27 0027.
 */
public class AddressModel extends SugarRecord{

    /**
     * detailAddress : 山西省长治市壶关县1小区
     * id : 1
     * name : 张三
     * phone : 123456789
     * region : 山西长治
     * userId : 2
     * zipCode : 47300
     */

    private String detailAddress;
    private String name;
    private String phone;
    private String region;
    private int userId;
    private int zipCode;
    private boolean isCheck;
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
