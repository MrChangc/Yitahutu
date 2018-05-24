package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.View.PayPsdInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\21 0021.
 */
public class PayNiuActivity extends BaseActivity implements PayPsdInputView.onPasswordListener {
    @BindView(R.id.psd)
    PayPsdInputView psd;
    @BindView(R.id.ll_confirm)
    LinearLayout llConfirm;
    private String psd_first;
    private String psd_last;
    private boolean isFirst;
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("牛币支付", R.layout.pay_niu_activity);
        ButterKnife.bind(this);
        psd.setComparePassword(this);

    }

    @Override
    public void onDifference(String oldPsd, String newPsd) {

    }

    @Override
    public void onEqual(String psd) {

    }

    @Override
    public void inputFinished(String inputPsd) {


    }

    @OnClick(R.id.ll_confirm)
    public void onViewClicked() {
        psd.getPasswordString();
    }
}
