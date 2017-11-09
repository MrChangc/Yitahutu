package com.yitahutu.cn.webservice;

import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017\10\14 0014.
 */
public abstract class MyStringCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        String dataResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(dataResponse);
        String data = jsonObject.getString("datas");
        return data;
    }
}
