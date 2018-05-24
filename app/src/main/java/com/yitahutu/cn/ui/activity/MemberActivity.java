package com.yitahutu.cn.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
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
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.View.PaymentDialog;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\14 0014.
 */
public class MemberActivity extends BaseActivity {
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.tv_member_name)
    TextView tvMemberName;
    @BindView(R.id.tv_member_data)
    TextView tvMemberData;
    @BindView(R.id.ll_button1)
    LinearLayout llButton1;
    @BindView(R.id.ll_button2)
    LinearLayout llButton2;
    @BindView(R.id.ll_button3)
    LinearLayout llButton3;
    @BindView(R.id.ll_button4)
    LinearLayout llButton4;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("成为会员", R.layout.activity_member);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        UserInfoModel userInfoModel = MyApplication.getUserInfoModel();
        int vip = userInfoModel.getVip();

        if (vip == 0) {
            tvMemberName.setText(userInfoModel.getName());
            tvMemberData.setText("购买成为会员");
            ivImage.setImageResource(R.mipmap.member_vip_no);
        } else {
            tvMemberName.setText(userInfoModel.getName() + "会员您好");
            String date = TimeUtils.getTime7(userInfoModel.getVipExpireTime());
            tvMemberData.setText(date + "到期");
            ivImage.setImageResource(R.mipmap.member_vip);
        }
//        Picasso.with(this).load(userInfoModel.getUrl()).error(getResources().getDrawable(R.mipmap.member_head)).into(imageHead);
    }

    @OnClick({R.id.ll_button1, R.id.ll_button2, R.id.ll_button3, R.id.ll_button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_button1:
                showPaymentDialog(1, 10);
                break;
            case R.id.ll_button2:
                showPaymentDialog(2, 20);
                break;
            case R.id.ll_button3:
                showPaymentDialog(3, 30);
                break;
            case R.id.ll_button4:
                showPaymentDialog(4, 50);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showPaymentDialog(final int num, int price) {
//        if (MyApplication.getUserInfoModel() != null) {
        final PaymentDialog paymentDialog = new PaymentDialog(mContext, new PaymentDialog.BalanceOnClick() {
            @Override
            public void onBalance(String type) {
                WebService.buyVip(num + "", type, MemberActivity.this, new SuccessCallBack() {
                    @Override
                    public void callBack() {
                        WebService.getUserInfo(MemberActivity.this, new SuccessCallBack() {
                            @Override
                            public void callBack() {
                                initData();
                                Toast.makeText(mContext,"会员购买成功!",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void callBackToObject(Object o) {

                            }
                        });
                    }

                    @Override
                    public void callBackToObject(Object o) {

                    }
                });
            }
        });
        paymentDialog.create();
        if (MyApplication.getUserInfoModel() != null) {
            paymentDialog.setTextNumber(MyApplication.getUserInfoModel().getName());
        }
        paymentDialog.setTextCount(price + "");
        paymentDialog.setTextType("牛币");
        paymentDialog.show();
//        }else
//            Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
    }
}
