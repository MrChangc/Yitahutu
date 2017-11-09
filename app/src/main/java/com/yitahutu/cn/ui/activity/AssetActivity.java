package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class AssetActivity extends BaseActivity {
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.text_asset_count)
    TextView textAssetCount;
    @BindView(R.id.text_balance)
    TextView textBalance;
    @BindView(R.id.text_investment)
    TextView textInvestment;
    @BindView(R.id.text_commission)
    TextView textCommission;
    @BindView(R.id.share_out_bonus)
    TextView shareOutBonus;
    @BindView(R.id.text_income)
    TextView textIncome;
    @BindView(R.id.income_detail)
    TextView incomeDetail;
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
        setContentView("总资产明细", R.layout.activity_asset);
        ButterKnife.bind(this);
        setMiddleText("总资产明细");
    }
}
