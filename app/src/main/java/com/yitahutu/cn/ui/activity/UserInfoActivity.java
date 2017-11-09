package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yitahutu.cn.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class UserInfoActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("个人信息", R.layout.activity_user_info);
        ButterKnife.bind(this);
        setMiddleText("个人信息");
    }
}
