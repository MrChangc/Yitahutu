package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class SettingActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("设置", R.layout.activity_setting);
    }
}
