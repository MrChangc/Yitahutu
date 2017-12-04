package com.yitahutu.cn;

import com.orm.SugarApp;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.PhoneUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.UserInfoModel;

import java.util.List;

/**
 * Created by Administrator on 2017\10\19 0019.
 */
public class MyApplication extends SugarApp {
    private static UserInfoModel userInfoModel;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferUtil.Init(this);
        GoodsModel goodsModel = new GoodsModel();
        int state = PhoneUtils.getNetWorkStates(getApplicationContext());
        if (state == PhoneUtils.TYPE_MOBILE)
            PreferUtil.putNetWork(false);
        else{
            PreferUtil.putNetWork(true);
        }
    }

    public static UserInfoModel getUserInfoModel() {
        if (userInfoModel == null) {
            List<UserInfoModel> userInfoModels = UserInfoModel.listAll(UserInfoModel.class);
            if (userInfoModels.size() > 0) {
                userInfoModel = userInfoModels.get(0);
                return userInfoModel;
            } else
                return null;
        } else
            return userInfoModel;
    }

    public static void setUserInfoModel(UserInfoModel userInfoModel) {
        MyApplication.userInfoModel = userInfoModel;
    }
}
