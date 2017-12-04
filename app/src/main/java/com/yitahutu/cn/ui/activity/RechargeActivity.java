package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        setContentView("充值/提现",R.layout.activity_recharge);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_recharge, R.id.button_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_recharge:
                recharge();
                break;
            case R.id.button_withdraw:
                withdraw();
                break;
        }
    }

    private void withdraw() {
        Intent intent = new Intent(mContext,ReChargeDetailActivity.class);
        startActivity(intent);
    }

    private void recharge() {
    }
}
