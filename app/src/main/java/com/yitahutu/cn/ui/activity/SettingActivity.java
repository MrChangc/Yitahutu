package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.adapter.MyFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.ll_about)
    TextView llAbout;
    @BindView(R.id.ll_help)
    LinearLayout llHelp;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.ll_down_load)
    LinearLayout llDownLoad;
    @BindView(R.id.ll_custom_service)
    LinearLayout llCustomService;
    @BindView(R.id.ll_back_out)
    LinearLayout llBackOut;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("设置", R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_help, R.id.ll_user_info, R.id.ll_down_load, R.id.ll_custom_service, R.id.ll_back_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_help:
                break;
            case R.id.ll_user_info:
                Intent intent1 = new Intent(mContext,UserInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_down_load:
                break;
            case R.id.ll_custom_service:
                break;
            case R.id.ll_back_out:
                PreferUtil.putLogin(false);
                Intent intent = new Intent(mContext,UserLoginActivity.class);
                startActivity(intent);
                UserInfoModel.deleteAll(UserInfoModel.class);
                MyApplication.setUserInfoModel(null);
                break;
        }
    }
}
