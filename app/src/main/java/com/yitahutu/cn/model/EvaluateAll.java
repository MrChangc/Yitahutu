package com.yitahutu.cn.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class EvaluateAll extends SugarRecord{
    /**
     * badNum : 1
     * evaluationVo : [{"cliNum":2,"comment":"还行","createTime":1507860363672,"evaNum":2,"goodsId":1,"grade":2,"id":1,"pageView":100,"userId":2,"userName":"九牛寨用户430457","userUrl":"http://img0.imgtn.bdimg.com/it/u=1110101630,2488921246&fm=27&gp=0.jpg"},{"comment":"好奶","createTime":1507860363672,"goodsId":1,"grade":1,"id":2,"pageView":166,"userId":1}]
     * medNum : 1
     * count : 3
     * goodNum : 0
     */

    private int badNum;
    private int medNum;
    private int count;
    private int goodNum;
    @Ignore
    private List<EvaluateModel> evaluationVo;

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getMedNum() {
        return medNum;
    }

    public void setMedNum(int medNum) {
        this.medNum = medNum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public List<EvaluateModel> getEvaluationVo() {
        return evaluationVo;
    }

    public void setEvaluationVo(List<EvaluateModel> evaluationVo) {
        this.evaluationVo = evaluationVo;
    }


}
