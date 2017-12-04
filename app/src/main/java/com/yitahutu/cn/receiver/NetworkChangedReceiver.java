package com.yitahutu.cn.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.PhoneUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.Utils.ToastUtil;

public class NetworkChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int netWorkStates = PhoneUtils.getNetWorkStates(context);
        Log.e("netWorkStates",netWorkStates+"");
        switch (netWorkStates) {
            case PhoneUtils.TYPE_NONE:
                //断网了
                PreferUtil.putNetWork(false);
//                ToastUtil toastUtil = new ToastUtil(context, R.layout.view_toast,"亲，断网了!");
//                toastUtil.show();
                break;
            case PhoneUtils.TYPE_MOBILE:
                PreferUtil.putNetWork(true);
                //打开了移动网络
                break;
            case PhoneUtils.TYPE_WIFI:
                PreferUtil.putNetWork(true);
                //打开了WIFI
                break;

            default:
                break;
        }
    }
}
