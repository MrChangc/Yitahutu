package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yitahutu.cn.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class ShoppingAllActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("全部", R.layout.activity_shopp_all);
        ButterKnife.bind(this);
        setMiddleText("全部");
    }
}
