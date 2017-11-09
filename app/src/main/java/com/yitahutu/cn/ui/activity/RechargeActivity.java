package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class RechargeActivity extends BaseActivity {
    @BindView(R.id.text_money_count)
    TextView textMoneyCount;
    @BindView(R.id.button_recharge)
    Button buttonRecharge;
    @BindView(R.id.button_withdraw)
    Button buttonWithdraw;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
    }
}
