package com.yitahutu.cn.webservice;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public abstract class JsonObjectCallBack extends Callback<JSONObject> {
    @Override
    public JSONObject parseNetworkResponse(Response response, int id) throws Exception {
        String data = response.body().string();
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject;
    }
}
