package com.yitahutu.cn.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.ConsumeModel;
import com.yitahutu.cn.model.EvaluateAll;
import com.yitahutu.cn.model.ExpressModel;
import com.yitahutu.cn.model.FinanceDetailModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.model.RefundRecordModel;
import com.yitahutu.cn.model.TotalModel;
import com.yitahutu.cn.model.UserInfoModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017\10\16 0016.
 */
public class GsonUtils {
    public static List<GoodsModel> parserJsonArrayToGoodsModel(String strJson, Class<GoodsModel> jbDemo) {
        List<GoodsModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            GoodsModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }

    public static List<RecommendModel> parserJsonArrayToRecommendModel(String strJson, Class<RecommendModel> jbDemo) {
        List<RecommendModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            RecommendModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }

    public static EvaluateAll parserJsonObjectToEvaluateAll(String strJson) {
        Gson gson = new Gson();
        EvaluateAll evaluateAll = gson.fromJson(strJson, EvaluateAll.class);
        return evaluateAll;
    }
    public static TotalModel parserJsonObjectToTotalModel(String strJson) {
        Gson gson = new Gson();
        TotalModel evaluateAll = gson.fromJson(strJson, TotalModel.class);
        return evaluateAll;
    }
    public static ExpressModel parserJsonObjectToExpressModel(String strJson) {
        Gson gson = new Gson();
        ExpressModel evaluateAll = gson.fromJson(strJson, ExpressModel.class);
        return evaluateAll;
    }
    public static UserInfoModel parserJsonObjectToUserInfoModel(String strJson) {
        Gson gson = new Gson();
        UserInfoModel userInfoModel = gson.fromJson(strJson, UserInfoModel.class);
        return userInfoModel;
    }
    public static List<FinanceModel> parserJsonArrayToFinanceModel(String strJson, Class<FinanceModel> jbDemo) {
        List<FinanceModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            FinanceModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }
    public static List<FinanceDetailModel> parserJsonArrayToFinanceDetailModel(String strJson, Class<FinanceDetailModel> jbDemo) {
        List<FinanceDetailModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            FinanceDetailModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }
    public static List<CartListModel> parserJsonArrayToCartList(String strJson, Class<CartListModel> jbDemo) {
        List<CartListModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            CartListModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }
    public static List<AddressModel> parserJsonArrayToAddress(String strJson, Class<AddressModel> jbDemo) {
        List<AddressModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            AddressModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }
    public static List<RefundRecordModel> parserJsonArrayToRefundRecordModel(String strJson, Class<RefundRecordModel> jbDemo) {
        List<RefundRecordModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            RefundRecordModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }
    public static List<ConsumeModel> parserJsonArrayToConsumeModel(String strJson, Class<ConsumeModel> jbDemo) {
        List<ConsumeModel> goodsModels = new ArrayList<>();
        //创建一个Gson对象
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(strJson);

        //把JsonElement对象转换成JsonObject
        JsonObject jsonObj = null;
        if (el.isJsonObject()) {
            jsonObj = el.getAsJsonObject();
        }


        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            ConsumeModel goodsModel = gson.fromJson(e, jbDemo);
            goodsModels.add(goodsModel);
        }
        return goodsModels;
    }

}
