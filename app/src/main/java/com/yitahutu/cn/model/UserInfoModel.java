package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class UserInfoModel extends SugarRecord{
    /**
     * createTime : 1507775985368
     * id : 3
     * integral : 100
     * name : 九牛寨用户430457
     * password : 202cb962ac59075b964b07152d234b70
     * phone : 123456789
     * region : 山西长治
     * sex : 1
     * url : http://img0.imgtn.bdimg.com/it/u=1110101630,2488921246&fm=27&gp=0.jpg
     * vip : 0
     * wallet : 1000
     */

    private long createTime;
    private int integral;
    private String name;
    private String password;
    private String phone;
    private String region;
    private int sex;
    private String url;
    private int vip;
    private int wallet;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }



    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }


}
