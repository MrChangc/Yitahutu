package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2017\10\14 0014.
 */
public class MemberActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("成为会员", R.layout.activity_member);
    }
}
