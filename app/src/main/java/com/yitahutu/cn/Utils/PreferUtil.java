package com.yitahutu.cn.Utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.model.UserInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/1.
 */
public class PreferUtil {
    private static SharedPreferencesHelper spHelper = null;

    public static void Init(Context mContext) {
        spHelper = SharedPreferencesHelper.getInstance(mContext);
    }

    public static void clean() {
        spHelper.clear();
    }

    public static void setToken(String key, String token) {
        spHelper.putString(key, token);
    }

    public static String getToken(String key) {
        return spHelper.getString(key, null);
    }

    public static void setUserName(String userName) {
        spHelper.putString("userName", userName);
    }

    public static String getUserName() {
        return spHelper.getString("userName", null);
    }

    public static void setUserInfoModel(String userInfoModel) {
        spHelper.putString("userName", userInfoModel);
    }

    public static UserInfoModel getUserInfoModel() {
        String user = spHelper.getString("userName", null);
        UserInfoModel userInfoModel = new Gson().fromJson(user, UserInfoModel.class);
        return userInfoModel;
    }

    public static void setRecommendModelList(String list) {
        spHelper.putString("RecommendModelList", list);
    }

    public static List<RecommendModel> getRecommendModelList() {
        String json = spHelper.getString("RecommendModelList", null);
        if (json != null && !TextUtils.isEmpty(json))
            return GsonUtils.parserJsonArrayToRecommendModel(json, RecommendModel.class);
        else
            return new ArrayList<>();
    }

    public static boolean isLogin() {
        return spHelper.getBoolean("is_login", false);
    }

    public static void putLogin(boolean isLogin) {
        spHelper.putBoolean("is_login", isLogin);
    }
    public static boolean isNetWork() {
        return spHelper.getBoolean("is_network", false);
    }

    public static void putNetWork(boolean isLogin) {
        spHelper.putBoolean("is_network", isLogin);
    }


}
