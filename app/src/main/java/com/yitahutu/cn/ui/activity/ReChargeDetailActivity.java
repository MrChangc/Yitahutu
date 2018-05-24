package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.MachUtil;
import com.yitahutu.cn.Utils.PayUtils;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\11\14 0014.
 */
public class ReChargeDetailActivity extends BaseActivity implements SuccessCallBack {
    @BindView(R.id.radio_weichat)
    RadioButton radioWeichat;
    @BindView(R.id.ll_weichat)
    LinearLayout llWeichat;
    @BindView(R.id.radio_zhifubao)
    RadioButton radioZhifubao;
    @BindView(R.id.ll_zhifubao)
    LinearLayout llZhifubao;
    @BindView(R.id.edit_money)
    EditText editMoney;
    private String way ;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = "";
        title = getIntent().getStringExtra("title");
        setContentView(title, R.layout.activity_recharge_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_weichat, R.id.ll_zhifubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_weichat:
                if (radioWeichat.isChecked())
                    return;
                else {
                    radioWeichat.setChecked(true);
                    radioZhifubao.setChecked(false);
                }
                break;
            case R.id.ll_zhifubao:
                if (radioZhifubao.isChecked())
                    return;
                else {
                    radioWeichat.setChecked(false);
                    radioZhifubao.setChecked(true);
                }
                break;
        }
    }
    public void next (View v){
        String money = editMoney.getText().toString().trim();
        if (money == null&& TextUtils.isEmpty(money)){
            Toast.makeText(mContext,"价格不能为空!",Toast.LENGTH_SHORT).show();
        }else if (!MachUtil.isNumber(money)){
            Toast.makeText(mContext,"请输入正确价格!",Toast.LENGTH_SHORT).show();
        }else {
            recharge(money);
        }
    }

    private void recharge(String money) {
        if (radioZhifubao.isChecked()){
            way = "1";
        }else
            way = "2";
        WebService.userRecharge(money,way,mContext,this);
    }

    @Override
    public void callBack() {

    }

    @Override
    public void callBackToObject(Object o) {
        if (way.equals("2")){
            String order = (String) o;
            PayUtils.weiChatPay(mContext,order);
        }
    }
}
