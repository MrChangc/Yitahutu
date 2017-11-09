package com.yitahutu.cn.webservice;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.GsonUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.EvaluateAll;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.View.QdLoadingDialog;
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
                            if (code == 1)
                                message = "手机号不能为空";
                            else if (code == 2)
                                message = "没有选择类型";
                            else if (code == 3)
                                message = "用户还没组册";
                            else if (code == 4)
                                message = "用户还没组册";
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void register(final Context mContext, String phoneNumber, String authCode,String type,String password,String surePass) {
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
                            if (code == 1)
                                message = "手机号不能为空";
                            else if (code == 2)
                                message = "密码不能为空";
                            else if (code == 3)
                                message = "验证码不能为空";
                            else if (code == 4)
                                message = "验证码不正确";
                            else if (code == 5)
                                message = "两次输入密码不一致";
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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

    public static void getUserInfo( final Context mContext) {
        String token = null;
//        if (PreferUtil.isLogin())
//            token = PreferUtil.getToken(PreferUtil.getUserName());
//        else
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

    public static void getGoodsList(final Context mContext) {
        String token = null;
//        if (PreferUtil.isLogin())
//            token = PreferUtil.getToken(PreferUtil.getUserName());
//        else
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.GoodsByRecommendEvent(goodsModels));
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void getGoodsListByScreenType(final Context mContext, int id ,int type) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void getGoodsListByScreenType(final Context mContext, int id ,int type,int sortType) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void getGoodsListByPriceRange(final Context mContext, int type,String max,String mix) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }



    public static void getEvaluateList(final Context mContext, String id) {
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                List<GoodsModel> goodsModels = GsonUtils.parserJsonArrayToGoodsModel(data, GoodsModel.class);
                                for (GoodsModel goodsModel : goodsModels) {
                                    if (goodsModel != null) {
                                        if (goodsModel.getCoverUrl() != null && !TextUtils.isEmpty(goodsModel.getCoverUrl()))
                                            images.add(ConstantUtils.baseUrl+goodsModel.getCoverUrl());
                                    }
                                }
                                if (goodsModels.size() > 0)
                                    EventBus.getDefault().post(new Event.RecommendGoodsEvent());
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                message = "获取商品成功";
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    PreferUtil.setRecommendModelList(data);
                                    EventBus.getDefault().post(new Event.RecommendGoodsEvent());
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    message = data;
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void getFinanceList(final Context mContext) {
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
                                    List<FinanceModel> financeModels = GsonUtils.parserJsonArrayToFinanceModel(data, FinanceModel.class);
                                    FinanceModel.deleteAll(FinanceModel.class);
                                    FinanceModel.saveInTx(financeModels);
                                    EventBus.getDefault().post(new Event.FinanceEvent(financeModels));
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void getCartList(final int state , final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/cart/list")
                .addParams("state", state+"")
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<CartListModel> financeModels = GsonUtils.parserJsonArrayToCartList(data, CartListModel.class);
                                    CartListModel.deleteAll(CartListModel.class);
                                    CartListModel.saveInTx(financeModels);
                                    EventBus.getDefault().post(new Event.CartListEvent(financeModels,state));
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void deleteCartList(String id ,final Context mContext) {
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "cart/delete")
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    message = data;
                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void addCartList(String id ,String num ,final Context mContext,JsonObjectCallBack objectCallBack) {
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
    public static void addGoodsToCart(String id ,String num ,JsonObjectCallBack objectCallBack) {
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
                .execute(objectCallBack);
    }
    public static void buyGoods(String addressId , String id , String num , String way, final Context mContext) {
        loading(mContext);
        String token = null;
        if (PreferUtil.isLogin())
            token = PreferUtil.getToken(PreferUtil.getUserName());
        else
            token = ConstantUtils.default_token;
        OkHttpUtils
                .post()
                .url(ConstantUtils.baseUrl + "/goods/buyGoods")
                .addParams("id", id)
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }
                });
    }
    public static void buyCart(String addressId , String id , String num , String way, final Context mContext) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }
                });
    }
    public static void refund(String id ,final Context mContext) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                    message = "退款成功";
//                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void financeAdopt(String way,String id ,final Context mContext) {
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                                    message = "领养成功";
//                                }
                            }
                        } catch (JSONException e) {
                            message = "请求错误";
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<AddressModel> addressModels = GsonUtils.parserJsonArrayToAddress(data, AddressModel.class);
                                    if (addressModels.size()>0){
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
                                    if (addressModels.size()>0){
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
                            else if (code == 200) {
                                String data = response.getString("datas");
                                if (data != null && !TextUtils.isEmpty(data)) {
                                    List<FinanceModel> addressModels = GsonUtils.parserJsonArrayToFinanceModel(data, FinanceModel.class);
                                    if (addressModels.size()>0){
                                        FinanceModel.deleteAll(FinanceModel.class);
                                        FinanceModel.saveInTx(addressModels);
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
                                    if (addressModels.size()>0){
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
                                     String id ,
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
                            if (code == 1)
                                message = "参数为空";
                            else if (code == 300)
                                message = "登录过期";
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
                        }
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
}
