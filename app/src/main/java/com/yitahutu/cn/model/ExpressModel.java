package com.yitahutu.cn.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\11\30 0030.
 */
public class ExpressModel {

    /**
     * list : [{"time":"2015-10-20 10:24:04","status":"顺丰速运 已收取快件"},{"time":"2015-10-20 11:49:26","status":"快件离开【广州龙怡服务点】,正发往 【广州番禺集散中心】"},{"time":"2015-10-21 09:22:10","status":"已签收,感谢使用顺丰,期待再次为您服务"},{"time":"2015-10-21 09:22:10","status":"在官网\"运单资料&签收图\",可查看签收人信息"}]
     * issign : 1
     */

    private String issign;
    private ArrayList<ExpressState> list;

    public String getIssign() {
        return issign;
    }

    public void setIssign(String issign) {
        this.issign = issign;
    }

    public ArrayList<ExpressState> getList() {
        return list;
    }

    public void setList(ArrayList<ExpressState> list) {
        this.list = list;
    }

    public static class ExpressState {
        /**
         * time : 2015-10-20 10:24:04
         * status : 顺丰速运 已收取快件
         */

        private String time;
        private String status;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
