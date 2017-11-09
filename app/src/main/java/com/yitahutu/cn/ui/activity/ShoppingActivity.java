package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class ShoppingActivity extends BaseActivity {
    @BindView(R.id.ll_mall_detail_all)
    LinearLayout llMallDetailAll;
    @BindView(R.id.ll_mall_detail_obligation)
    LinearLayout llMallDetailObligation;
    @BindView(R.id.ll_mall_detail_delivery)
    LinearLayout llMallDetailDelivery;
    @BindView(R.id.ll_mall_detail_receiver)
    LinearLayout llMallDetailReceiver;
    @BindView(R.id.ll_mall_detail_evaluate)
    LinearLayout llMallDetailEvaluate;
    @BindView(R.id.ll_mall_detail_refund)
    LinearLayout llMallDetailRefund;
    @BindView(R.id.ll_mall_detail_coupon)
    LinearLayout llMallDetailCoupon;
    @BindView(R.id.ll_mall_detail_my_share)
    LinearLayout llMallDetailMyShare;
    @BindView(R.id.ll_mall_detail_member)
    LinearLayout llMallDetailMember;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("商城明细", R.layout.activity_shopping);
        ButterKnife.bind(this);
        setMiddleText("商城明细");
    }

    @OnClick({R.id.ll_mall_detail_all, R.id.ll_mall_detail_obligation, R.id.ll_mall_detail_delivery, R.id.ll_mall_detail_receiver, R.id.ll_mall_detail_evaluate, R.id.ll_mall_detail_refund, R.id.ll_mall_detail_coupon, R.id.ll_mall_detail_my_share, R.id.ll_mall_detail_member})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mall_detail_all:
                gotoCartActivity(0);
                break;
            case R.id.ll_mall_detail_obligation:
                gotoCartActivity(1);
                break;
            case R.id.ll_mall_detail_delivery:
                gotoCartActivity(2);
                break;
            case R.id.ll_mall_detail_receiver:
                gotoCartActivity(3);
                break;
            case R.id.ll_mall_detail_evaluate:
                gotoCartActivity(4);
                break;
            case R.id.ll_mall_detail_refund:
                break;
            case R.id.ll_mall_detail_coupon:
                break;
            case R.id.ll_mall_detail_my_share:
                break;
            case R.id.ll_mall_detail_member:
                break;
        }
    }
    private void gotoActivity(Class aClass){
        Intent intent = new Intent(this,aClass);
        startActivity(intent);
    }
    private void gotoCartActivity(int page){
        Intent intent = new Intent(this,MallDetailBaseActivity.class);
        intent.putExtra("page",page);
        startActivity(intent);
    }
}
