package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.GsonUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.webservice.JsonObjectCallBack;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017\10\14 0014.
 */
public class UserLoginActivity extends BaseActivity {
    @BindView(R.id.edit_user_name)
    EditText editUserName;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.text_forget_password)
    TextView textForgetPassword;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_member)
    TextView textMember;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("账号登录", R.layout.activity_login);
        ButterKnife.bind(this);
        setRightTextVisibility(true);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void login(View view) {
        final String userName = editUserName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if (userName != null && password != null)
            WebService.login(userName, password, new JsonObjectCallBack() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(JSONObject response, int id) {
                    String message = "";
                    try {
                        int code = response.getInt("code");
                        if (code == 1)
                            message = "手机号不能为空";
                        else if (code == 2)
                            message = "密码不能为空";
                        else if (code == 3)
                            message = "手机号未注册";
                        else if (code == 4)
                            message = "密码不正确";
                        else if (code == 200) {
                            message = "登录成功";
                            String data = response.getString("datas");
                            JSONObject jsonObject = new JSONObject(data);
                            String token = jsonObject.getString("token");
                            PreferUtil.setToken(userName, token);
                            PreferUtil.setUserName(userName);
                            PreferUtil.putLogin(true);
                            EventBus.getDefault().post(new Event.LoginEvent());
                            WebService.getUserInfoModel(token, new JsonObjectCallBack() {
                                String message = "";

                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    message = "获取用户信息失败！";
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(JSONObject response, int id) {
                                    try {
                                        int code = response.getInt("code");
                                        if (code == 1)
                                            message = "参数为空";
                                        else if (code == 300)
                                            message = "登录过期";
                                        else if (code == 200) {
                                            message = "获取用户信息成功";
                                            String data = response.getString("datas");
                                            UserInfoModel userInfoModel = GsonUtils.parserJsonObjectToUserInfoModel(data);
                                            if (userInfoModel != null)
                                                userInfoModel.save();
                                            EventBus.getDefault().post(new Event.UserInfoEvent());
                                            WebService.getAddressList(mContext, new SuccessCallBack() {
                                                @Override
                                                public void callBack() {
                                                    Intent intent =new Intent(UserLoginActivity.this,MainActivity.class);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void callBackToObject(Object o) {

                                                }
                                            });
                                        }
                                    } catch (JSONException e) {
                                        message = "请求错误";
                                        e.printStackTrace();
                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        message = "请求错误";
                        e.printStackTrace();
                    }
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                }
            });
    }

    @OnClick(R.id.text_register)
    public void setTextRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.text_member)
    public void setTextMember() {
        Intent intent = new Intent(this, MemberActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.text_forget_password)
    public void setTextForgetPassword() {
        String phone = editUserName.getText().toString().trim();
        WebService.sendMember(this, phone, "1");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(UserLoginActivity.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
