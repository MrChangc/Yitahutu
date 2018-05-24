package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.SignModel;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\12\12 0012.
 */
public class SignActivity extends BaseActivity implements SuccessCallBack {
    @BindView(R.id.image_sign_rule)
    ImageView imageSignRule;
    @BindView(R.id.text_sign_rule)
    TextView textSignRule;
    @BindView(R.id.ll_sign_rule)
    LinearLayout llSignRule;
    @BindView(R.id.text_data_number)
    TextView textDataNumber;
    @BindView(R.id.text_integral)
    TextView textIntegral;
    @BindView(R.id.button_sign)
    Button buttonSign;
    @BindView(R.id.ll_sign_pasture)
    LinearLayout llSignPasture;
    @BindView(R.id.image_member)
    LinearLayout imageMember;
    @BindView(R.id.image_invitation)
    LinearLayout imageInvitation;
    @BindView(R.id.image_exchange)
    LinearLayout imageExchange;
    private SignModel signModel;
    private int type;
    private long creatTime;
    private int integral;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("优惠会员", R.layout.activity_sign);
        ButterKnife.bind(this);
        WebService.getSign(mContext, this);
    }

    @OnClick({R.id.button_sign, R.id.ll_sign_pasture,R.id.image_member, R.id.image_invitation, R.id.image_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_sign:
                sign();
                break;
            case R.id.ll_sign_pasture:
                gotoPasture();
                break;
            case R.id.image_member:
                gotoMemBerActivity();
                break;
            case R.id.image_invitation:
                gotoInvitation();
                break;
            case R.id.image_exchange:
                break;
        }
    }

    private void gotoInvitation() {
        Intent intent = new Intent(mContext,HongBaoActivity.class);
        startActivity(intent);
    }

    private void gotoPasture() {
        Intent intent = new Intent(mContext,FinancingPastureActivity.class);
        startActivity(intent);
    }

    private void sign() {
        WebService.sign(mContext, type + "", creatTime + "", integral + "", this);
    }

    private void gotoMemBerActivity() {
        Intent intent = new Intent(mContext, MemberActivity.class);
        startActivity(intent);
    }

    @Override
    public void callBack() {

    }

    @Override
    public void callBackToObject(Object o) {
        if (o instanceof SignModel) {
            signModel = (SignModel) o;
            textDataNumber.setText(signModel.getSign().getDayNum() + "天");
            textIntegral.setText(signModel.getTotalIntegral() + "");
            String time = TimeUtils.getTime4(System.currentTimeMillis());
            long currentTime = TimeUtils.time2long(time);
            if (signModel != null) {
                long time_disparity = currentTime - signModel.getSign().getCreateTime();
                if (time_disparity <= 0) {
                    buttonSign.setEnabled(false);
                    Toast.makeText(mContext, "今日已签到", Toast.LENGTH_SHORT).show();
                    buttonSign.setText("今日已签到");
                    buttonSign.setBackgroundColor(SignActivity.this.getResources().getColor(R.color.gray));
                    buttonSign.setTextColor(SignActivity.this.getResources().getColor(R.color.black));
                } else if (0 < time_disparity && time_disparity <= 86400000) {
                    buttonSign.setEnabled(true);
                    type = 1;
                    creatTime = currentTime;
                    if (signModel.getSign().getDayNum() >= 7)
                        integral = 1;
                    else
                        integral = signModel.getSign().getDayNum() + 1;
                    buttonSign.setText("每日签到");
                    buttonSign.setBackgroundColor(SignActivity.this.getResources().getColor(R.color.title_back_ground));
                    buttonSign.setTextColor(SignActivity.this.getResources().getColor(R.color.white));
                } else if (time_disparity > 86400000) {
                    buttonSign.setEnabled(true);
                    type = 0;
                    creatTime = currentTime;
                    buttonSign.setText("每日签到");
                    buttonSign.setBackgroundColor(SignActivity.this.getResources().getColor(R.color.title_back_ground));
                    buttonSign.setTextColor(SignActivity.this.getResources().getColor(R.color.white));
                    Toast.makeText(mContext,"未连续签到!",Toast.LENGTH_SHORT).show();
//                    buttonSign.setText("未连续签到");
                    textDataNumber.setText("0 天");
                }
            }
        }
        if (o instanceof String) {
            WebService.getSign(mContext, this);
        }
    }
}
