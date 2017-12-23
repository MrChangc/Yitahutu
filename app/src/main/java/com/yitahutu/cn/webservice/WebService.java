package com.yitahutu.cn.webservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.GsonUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.ConsumeModel;
import com.yitahutu.cn.model.EvaluateAll;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.ExpressModel;
import com.yitahutu.cn.model.FinanceDetailModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.model.RefundRecordModel;
import com.yitahutu.cn.model.SignModel;
import com.yitahutu.cn.model.TotalModel;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.View.QdLoadingDialog;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.activity.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class WebService {


    public static void sendMember(final Context mContext, String phoneNumber, String type) {
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/smsmessage")
                .addParams("phone", phoneNumber)
                .addParams("type", type)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1){
                                message = "手机号不能为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 2){
                                message = "没有选择类型";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 3){
                                message = "用户还没组册";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 4){
                                message = "用户还没组册";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void register(final Context mContext, String phoneNumber, String authCode, String type, String password, String surePass) {
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/register")
                .addParams("phone", phoneNumber)
                .addParams("authCode", authCode)
                .addParams("type", type)
                .addParams("password", password)
                .addParams("surePass", surePass)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1){
                                message = "手机号不能为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 2){
                                message = "密码不能为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 3){
                                message = "验证码不能为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 4){
                                message = "验证码不正确";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 5){
                                message = "两次输入密码不一致";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "注册成功!";
                                Intent intent = new Intent(mContext, MainActivity.class);
                                mContext.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void login(String phoneNumber, String password, JsonObjectCallBack objectCallBack) {
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/login")
                .addParams("phone", phoneNumber)
                .addParams("password", password)
                .build()
                .execute(objectCallBack);
    }

    public static void getUserInfoModel(String token, JsonObjectCallBack objectCallBack) {
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/person")
                .addParams("token", token)
                .build()
                .execute(objectCallBack);
    }

    public static void getUserInfo(final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/person")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        String message = "";
                        try {
                            int code = response.getInt("code");
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                message = "登录成功";
                                String data = response.getString("datas");
                                UserInfoModel userInfoModel = new Gson().fromJson(data, UserInfoModel.class);
                                PreferUtil.setUserInfoModel(data);
                                EventBus.getDefault().post(new Event.UserInfoEvent());
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void getUserInfo(final Context mContext, final SuccessCallBack successCallBack) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/person")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        String message = "";
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "登录成功";
                                String data = response.getString("datas");
                                UserInfoModel userInfoModel = new Gson().fromJson(data, UserInfoModel.class);
                                PreferUtil.setUserInfoModel(data);
                                MyApplication.setUserInfoModel(userInfoModel);
                                successCallBack.callBack();
                                EventBus.getDefault().post(new Event.UserInfoEvent());
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsList(final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0) {
                                    GoodsModel.deleteAll(GoodsModel.class);
                                    GoodsModel.saveInTx(goodsModels);
                                    EventBus.getDefault().post(new Event.GoodsEvent(goodsModels));
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsListByRecommend(final Context mContext, int id) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .addParams("recommendId", id + "")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                EventBus.getDefault().post(new Event.GoodsByRecommendEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsListBySerch(final Context mContext,
                                           String name,
                                           String recommendId,
                                           String screenType,
                                           String typeId,
                                           String sortType,
                                           String max,
                                           String mix,
                                           final RefreshableView refreshableView) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .addParams("name", name)
                .addParams("recommendId", recommendId)
                .addParams("screenType", screenType)
                .addParams("sortType", sortType)
                .addParams("typeId", typeId)
                .addParams("maxPrice", max)
                .addParams("minPrice", mix)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        if (refreshableView != null)
                            refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.GoodsBySearch(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        if (refreshableView != null)
                            refreshableView.finishRefreshing();
                    }
                });
    }

    public static void getGoodsListByScreenType(final Context mContext, int id, int type) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .addParams("screenType", id + "")
                .addParams("typeId", type + "")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.GoodsByScreenEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsListByScreenType(final Context mContext, int id, int type, int sortType) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .addParams("screenType", id + "")
                .addParams("typeId", type + "")
                .addParams("sortType", sortType + "")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.GoodsByScreenEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsListByPriceRange(final Context mContext, int type, String max, String mix) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/list")
                .addParams("token", token)
                .addParams("typeId", type + "")
                .addParams("maxPrice", max)
                .addParams("minPrice", mix)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.GoodsByScreenEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public static void getEvaluateList(final Context mContext, String id, final RefreshableView refreshableView) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/evaluation/list")
                .addParams("token", token)
                .addParams("goodsId", id)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取评论成功";
                                String data = response.getString("datas");
                                EvaluateAll evaluateAll = GsonUtils.parserJsonObjectToEvaluateAll(data);
                                evaluateAll.save();
                                List<EvaluateModel> evaluateModels = evaluateAll.getEvaluationVo();
                                EvaluateModel.deleteAll(EvaluateModel.class);
                                EvaluateModel.saveInTx(evaluateModels);
                                if (evaluateModels.size() > 0)
                                    EventBus.getDefault().post(new Event.EvaluateEvent(evaluateModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }
                });
    }

    public static void getEvaluateList(final Context mContext, String id, String grade, final RefreshableView refreshableView) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/evaluation/list")
                .addParams("token", token)
                .addParams("goodsId", id)
                .addParams("grade", grade)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取评论成功";
                                String data = response.getString("datas");
                                EvaluateAll evaluateAll = GsonUtils.parserJsonObjectToEvaluateAll(data);
                                evaluateAll.save();
                                List<EvaluateModel> evaluateModels = evaluateAll.getEvaluationVo();
                                EvaluateModel.deleteAll(EvaluateModel.class);
                                EvaluateModel.saveInTx(evaluateModels);
                                if (evaluateModels.size() > 0)
                                    EventBus.getDefault().post(new Event.EvaluateEvent(evaluateModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }
                });
    }

    public static void getRecommendGoodsList(final Context mContext, final List<String> images) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/recomList")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                for (GoodsModel goodsModel : goodsModels) {
                                    if (goodsModel != null) {
                                        if (goodsModel.getCoverUrl() != null && !TextUtils.isEmpty(goodsModel.getCoverUrl()))
                                            images.add(ConstantUtils.baseUrl + goodsModel.getCoverUrl());
                                    }
                                }
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.RecommendGoodsEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void getRecommendList(final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/recommend/list")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    PreferUtil.setRecommendModelList(data);
                                    EventBus.getDefault().post(new Event.RecommendListEvent());
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getGoodsBestUp(final Context mContext, String id) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/secondLevel/add")
                .addParams("token", token)
                .addParams("evaluationId", id)
                .addParams("type", 2 + "")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    message = data;
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void getFinanceList(final Context mContext, final RefreshableView refreshableView) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/finance/list")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<FinanceModel> financeModels = GsonUtils.parserJsonArrayToFinanceModel(data, FinanceModel.class);
                                    FinanceModel.deleteAll(FinanceModel.class);
                                    FinanceModel.saveInTx(financeModels);
                                    EventBus.getDefault().post(new Event.FinanceEvent(financeModels));
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        refreshableView.finishRefreshing();
                    }
                });
    }

    public static void getCartList(final int state, final Context mContext, final RefreshableView refreshableView) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/list")
                .addParams("state", state + "")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<CartListModel> financeModels = GsonUtils.parserJsonArrayToCartList(data, CartListModel.class);
                                    CartListModel.deleteAll(CartListModel.class);
                                    CartListModel.saveInTx(financeModels);
                                    EventBus.getDefault().post(new Event.CartListEvent(financeModels, state));
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        refreshableView.finishRefreshing();
                    }
                });
    }

    public static void deleteCartList(String id, final Context mContext, final SuccessCallBack successCallBack) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/delete")
                .addParams("id", id)
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "删除成功";
//                                }
                                successCallBack.callBack();
                                mLoadingDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void addCartList(String id, String num, final Context mContext, JsonObjectCallBack objectCallBack) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/update")
                .addParams("id", id)
                .addParams("token", token)
                .addParams("num", num)
                .build()
                .execute(objectCallBack);
    }

    public static void addGoodsToCart(String id, String num, final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/add")
                .addParams("goodsId", id)
                .addParams("token", token)
                .addParams("num", num)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "加入购物车成功!";
//                                }
                                EventBus.getDefault().post(new Event.AddGoodsToCart());
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void buyGoods(String addressId, String id, String num, String way, final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/buyGoods")
                .addParams("goodsId", id)
                .addParams("addressId", addressId)
                .addParams("token", token)
                .addParams("num", num)
                .addParams("way", way)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "购买成功!";
//                                }
                                EventBus.getDefault().post(new Event.BuyGoodsEvent());
                            } else if (code == 2) {
                                message = "支付失败";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void buyCart(String addressId, String id, String way, final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/buyCart")
                .addParams("coCartIds", id)
                .addParams("addressId", addressId)
                .addParams("token", token)
                .addParams("way", way)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "购买成功!";
//                                }
                                EventBus.getDefault().post(new Event.BuyGoodsEvent());
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void refund(String id, final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/update")
                .addParams("id", id)
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "退款成功";
//                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void financeAdopt(String way, String id, String number, final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/finance/adopt")
                .addParams("financeId", id)
                .addParams("token", token)
                .addParams("number", number)
                .addParams("way", "0")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "领养成功";
//                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void getAddressList(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/address/addressList")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<AddressModel> addressModels = GsonUtils.parserJsonArrayToAddress(data, AddressModel.class);
                                    if (addressModels.size() > 0) {
                                        AddressModel.deleteAll(AddressModel.class);
                                        AddressModel.saveInTx(addressModels);
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                        Activity activity = (Activity) mContext;
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                });
    }

    public static void getTypeList(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/type/list")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<AddressModel> addressModels = GsonUtils.parserJsonArrayToAddress(data, AddressModel.class);
                                    if (addressModels.size() > 0) {
                                        AddressModel.deleteAll(AddressModel.class);
                                        AddressModel.saveInTx(addressModels);
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        mLoadingDialog.dismiss();
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        Activity activity = (Activity) mContext;
                        activity.finish();
                    }
                });
    }

    public static void getUserFinanceList(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/finance/getUsercoFinance")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<FinanceDetailModel> addressModels = GsonUtils.parserJsonArrayToFinanceDetailModel(data, FinanceDetailModel.class);
                                    if (addressModels.size() > 0) {
                                        FinanceModel.deleteAll(FinanceModel.class);
                                        FinanceModel.saveInTx(addressModels);
                                        EventBus.getDefault().post(new Event.FinanceDetailEvent(addressModels));
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();

                    }
                });
    }

    public static void getRefundRecord(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/refundRecord")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<RefundRecordModel> addressModels = GsonUtils.parserJsonArrayToRefundRecordModel(data, RefundRecordModel.class);
                                    if (addressModels.size() > 0) {
                                        RefundRecordModel.deleteAll(RefundRecordModel.class);
                                        RefundRecordModel.saveInTx(addressModels);
                                        EventBus.getDefault().post(new Event.RefundRecordModelEvent(addressModels));
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void getUserUpdate(final Context mContext, String name, String address, String url, String sex) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/update")
                .addParams("token", token)
                .addParams("name", name)
                .addParams("url", url)
                .addParams("sex", sex)
                .addParams("region", address)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                message = "修改成功";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void getConsumeList(final Context mContext, String state, final RefreshableView refreshableView) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/consume/list")
                .addParams("token", token)
                .addParams("state", state)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<ConsumeModel> addressModels = GsonUtils.parserJsonArrayToConsumeModel(data, ConsumeModel.class);
                                    if (addressModels.size() > 0) {
                                        ConsumeModel.deleteAll(ConsumeModel.class);
                                        ConsumeModel.saveInTx(addressModels);
                                        EventBus.getDefault().post(new Event.ConsumeModelEvent(addressModels));
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                        refreshableView.finishRefreshing();
                    }
                });
    }

    public static void getUserTotalMoney(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/totalMoney")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    TotalModel totalModel = GsonUtils.parserJsonObjectToTotalModel(data);
                                    if (totalModel != null) {
                                        TotalModel.deleteAll(TotalModel.class);
                                        totalModel.save();
                                        EventBus.getDefault().post(new Event.TotalModelEvent());
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void getUserFinance(final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/type/list")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<AddressModel> addressModels = GsonUtils.parserJsonArrayToAddress(data, AddressModel.class);
                                    if (addressModels.size() > 0) {
                                        AddressModel.deleteAll(AddressModel.class);
                                        AddressModel.saveInTx(addressModels);
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        mLoadingDialog.dismiss();
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        Activity activity = (Activity) mContext;
                        activity.finish();
                    }
                });
    }

    public static void updateAddress(final Context mContext,
                                     String id,
                                     String name,
                                     String phone,
                                     String region,
                                     String detailAddress,
                                     String zipCode) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/address/addressList")
                .addParams("token", token)
                .addParams("id", id)
                .addParams("name", name)
                .addParams("phone", phone)
                .addParams("region", region)
                .addParams("detailAddress", detailAddress)
                .addParams("zipCode", zipCode)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
//                                    List<AddressModel> addressModels = GsonUtils.parserJsonArrayToAddress(data, AddressModel.class);
//                                    if (addressModels.size()>0){
//                                        AddressModel.deleteAll(AddressModel.class);
//                                        AddressModel.saveInTx(addressModels);
//                                    }
//
//                                }
                                message = "修改成功!";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                        Activity activity = (Activity) mContext;
                        activity.finish();
                    }
                });
    }

    private static QdLoadingDialog mLoadingDialog;

    private static void loading(Context mContext) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = new QdLoadingDialog(mContext, "加载中...");
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.show();
        }
    }

    public static void getExpress(final Context mContext,
                                  String number) {
        loading(mContext);

        OkHttpUtils
                .get()
                .url("http://jisukdcx.market.alicloudapi.com/express/query")
                .addHeader("Authorization", "APPCODE e80b1c89c35e4ee588ffb38588910ad5")
                .addParams("number", number)
                .addParams("type", "auto")
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            String data = response.getString("result");
                            ExpressModel expressModel = GsonUtils.parserJsonObjectToExpressModel(data);
                            ArrayList<ExpressModel.ExpressState> expressStates = expressModel.getList();
                            if (expressStates.size() > 0) {
                                EventBus.getDefault().post(new Event.ExpressModelEvent(expressStates));
                            }
                            message = "修改成功!";
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void getSign(final Context mContext, final SuccessCallBack successCallBack) {
        loading(mContext);

        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/sign/getSign")
                .addParams("token", token)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    PreferUtil.setSignModel(data);
                                    SignModel signModel = GsonUtils.parserJsonObjectToSignModel(data);
                                    successCallBack.callBackToObject(signModel);
                                }
                                message = "修改成功!";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void sign(final Context mContext, String type, String createTime, String integral,
                            final SuccessCallBack successCallBack) {
        loading(mContext);

        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/sign/sign")
                .addParams("token", token)
                .addParams("type", type)
                .addParams("integral", integral)
                .addParams("createTime", createTime)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();

                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            } else if (code == 300) {
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                            } else if (code == 200) {
//
                                String data = response.getString("message");

                                message = data;
                                successCallBack.callBackToObject(data);
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }

    public static void buyVip(String num, String way, final Context mContext, final SuccessCallBack successCallBack) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/user/buyVip")
                .addParams("token", token)
                .addParams("num", num)
                .addParams("way", way)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "购买成功!";
                                successCallBack.callBack();
//                                }
                                EventBus.getDefault().post(new Event.BuyGoodsEvent());
                            } else if (code == 2) {
                                message = "支付失败";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }
    public static void alipayStatic(String state, String orderNum, final Context mContext, final SuccessCallBack successCallBack) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/alipayStatic")
                .addParams("token", token)
                .addParams("state", state)
                .addParams("orderNum", orderNum)
                .build()
                .execute(new JsonObjectCallBack() {
                    String message = "";

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        message = "请求错误";
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(JSONObject response, int id) {
                        try {
                            int code = response.getInt("code");
                            if (code == 1) {
                                message = "参数为空";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 300){
                                message = "登录过期";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                message = "购买成功!";
                                successCallBack.callBack();
//                                }
                                EventBus.getDefault().post(new Event.BuyGoodsEvent());
                            } else if (code == 2) {
                                message = "支付失败";
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
    }
}
