package com.yitahutu.cn.model;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class EvaluateModel extends SugarRecord{

    /**
     * cliNum : 2
     * comment : 还行
     * createTime : 1507860363672
     * evaNum : 2
     * goodsId : 1
     * grade : 2
     * id : 1
     * pageView : 100
     * userId : 2
     * userName : 九牛寨用户430457
     * userUrl : http://img0.imgtn.bdimg.com/it/u=1110101630,2488921246&fm=27&gp=0.jpg
     */

    private int cliNum;
    private String comment;
    private long createTime;
    private int evaNum;
    private int goodsId;
    private int grade;
    private int pageView;
    private int userId;
    private String userName;
    private String userUrl;

    public int getCliNum() {
        return cliNum;
    }

    public void setCliNum(int cliNum) {
        this.cliNum = cliNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getEvaNum() {
        return evaNum;
    }

    public void setEvaNum(int evaNum) {
        this.evaNum = evaNum;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public int getPageView() {
        return pageView;
    }

    public void setPageView(int pageView) {
        this.pageView = pageView;
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

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
