package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class RecommendModel {

    /**
     * id : 1
     * name : 热销
     */

    private int id;
    private String name;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
