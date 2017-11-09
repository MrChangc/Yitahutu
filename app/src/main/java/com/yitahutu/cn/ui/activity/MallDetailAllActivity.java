package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class MallDetailAllActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("全部",R.layout.activity_mall_detail_all);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume",System.currentTimeMillis()+"");
    }
}
