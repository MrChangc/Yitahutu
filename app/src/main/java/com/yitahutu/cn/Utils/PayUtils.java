package com.yitahutu.cn.Utils;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018\1\8 0008.
 */
public class PayUtils {
    private static IWXAPI api;
    public static void weiChatPay(Context mContext,String order){
        init(mContext);
        JSONObject json = null;
        try {
            json = new JSONObject(order);
            PayReq req = new PayReq();
            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = "Sign=WXPay";
            req.sign = json.getString("sign");
            api.sendReq(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static IWXAPI init(Context mContext){
        if (api == null){
            api = WXAPIFactory.createWXAPI(mContext, "wx4bd3c0ec831f0b78", false);
            api.registerApp("wx4bd3c0ec831f0b78");
        }
        return api;
    }
}
