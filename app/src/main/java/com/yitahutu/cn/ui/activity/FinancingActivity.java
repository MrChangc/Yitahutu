package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class FinancingActivity extends BaseActivity {
    @BindView(R.id.ll_financing_all)
    LinearLayout llFinancingAll;
    @BindView(R.id.ll_financing_record)
    LinearLayout llFinancingRecord;
    @BindView(R.id.ll_financing_financing)
    LinearLayout llFinancingFinancing;
    @BindView(R.id.ll_financing_share_out_bonus)
    LinearLayout llFinancingShareOutBonus;
    @BindView(R.id.ll_financing_fund)
    LinearLayout llFinancingFund;
    @BindView(R.id.image_user_head)
    ImageView imageUserHead;
    @BindView(R.id.text_user_name)
    TextView textUserName;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("理财明细", R.layout.activity_financing);
        ButterKnife.bind(this);
        setMiddleText("理财明细");
        setRightIconVisibility(false);
        if(MyApplication.getUserInfoModel()!=null){
            textUserName.setText(MyApplication.getUserInfoModel().getName());
            Picasso.with(mContext).load(ConstantUtils.baseUrl+MyApplication.getUserInfoModel().getUrl()).into(imageUserHead);
        }

    }

    @OnClick({R.id.ll_financing_all, R.id.ll_financing_record, R.id.ll_financing_financing, R.id.ll_financing_share_out_bonus, R.id.ll_financing_fund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_financing_all:
                all();
                break;
            case R.id.ll_financing_record:
                record();
                break;
            case R.id.ll_financing_financing:
                financing();
                break;
            case R.id.ll_financing_share_out_bonus:
                break;
            case R.id.ll_financing_fund:
                Toast.makeText(mContext, "暂未开通!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void financing() {
        Intent intent = new Intent(mContext, FinanceDetailListActivity.class);
        intent.putExtra("type", 4);
        startActivity(intent);
    }

    private void record() {
        Intent intent = new Intent(mContext, ConsumeListActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }

    private void all() {
        Intent intent = new Intent(mContext, ConsumeListActivity.class);
        intent.putExtra("type", 4);
        startActivity(intent);
    }
}
