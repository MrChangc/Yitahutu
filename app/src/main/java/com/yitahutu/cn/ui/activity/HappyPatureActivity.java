package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2018\1\5 0005.
 */
public class HappyPatureActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("开心农场", R.layout.activity_happy_pasture);
    }
}
