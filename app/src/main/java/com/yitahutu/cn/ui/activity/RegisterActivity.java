package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\14 0014.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.setting_password)
    EditText settingPassword;
    @BindView(R.id.true_password)
    EditText truePassword;
    @BindView(R.id.identifying_code)
    EditText identifyingCode;
    @BindView(R.id.text_forget_password)
    TextView textForgetPassword;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_member)
    TextView textMember;
    @BindView(R.id.text_agree)
    TextView textAgree;
    @BindView(R.id.send_code)
    TextView sendCode;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("注册账号", R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void agree(View view) {
        String phoneNumber = editPhoneNumber.getText().toString().trim();
        String password = settingPassword.getText().toString().trim();
        String sure_password = truePassword.getText().toString().trim();
        String authCode = identifyingCode.getText().toString().trim();
        if (phoneNumber != null && !TextUtils.isEmpty(phoneNumber)
                && password != null && !TextUtils.isEmpty(password)
                && sure_password != null && !TextUtils.isEmpty(sure_password)
                && authCode != null && !TextUtils.isEmpty(authCode)
                )
            WebService.register(mContext, phoneNumber, authCode, "0", password, sure_password);
    }

    @OnClick(R.id.send_code)
    public void setSendCode() {
        String phoneNumber = editPhoneNumber.getText().toString().trim();
        if (phoneNumber != null && !TextUtils.isEmpty(phoneNumber))
            WebService.sendMember(mContext, phoneNumber, "0");
    }
}
