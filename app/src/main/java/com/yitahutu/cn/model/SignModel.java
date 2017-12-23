package com.yitahutu.cn.model;

/**
 * Created by Administrator on 2017\12\12 0012.
 */
public class SignModel {


    /**
     * totalIntegral : 10000
     * Sign : {"createTime":0,"dayNum":0}
     */

    private int totalIntegral;
    private Sign Sign;

    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public Sign getSign() {
        return Sign;
    }

    public void setSign(Sign Sign) {
        this.Sign = Sign;
    }

    public static class Sign {
        /**
         * createTime : 0
         * dayNum : 0
         */

        private long createTime;
        private int dayNum;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDayNum() {
            return dayNum;
        }

        public void setDayNum(int dayNum) {
            this.dayNum = dayNum;
        }
    }
}
