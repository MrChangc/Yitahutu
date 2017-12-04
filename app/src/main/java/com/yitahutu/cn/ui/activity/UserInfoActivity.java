package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.edit_user_name)
    EditText editUserName;
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.radio_man)
    RadioButton radioMan;
    @BindView(R.id.radio_woman)
    RadioButton radioWoman;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.image_add_address)
    ImageView imageAddAddress;
    @BindView(R.id.image_add_bank_card)
    ImageView imageAddBankCard;

    @Override
    void setRightIconListener() {
        if ("编辑".equals(getRightText())) {
            setRightText("完成");
            editUserName.setVisibility(View.VISIBLE);
            editAddress.setVisibility(View.VISIBLE);
            textUserName.setVisibility(View.GONE);
            textAddress.setVisibility(View.GONE);
            UserInfoModel infoModel = MyApplication.getUserInfoModel();
            if (infoModel != null) {
                editUserName.setText(infoModel.getName());
                editUserName.setText(infoModel.getRegion());
                if (infoModel.getSex() == 1)
                    radioMan.setChecked(true);
                else
                    radioWoman.setChecked(true);
            }
        } else if ("完成".equals(getRightText())) {
            String user_name = editUserName.getText().toString();
            String address = editAddress.getText().toString();
            if (user_name == null && TextUtils.isEmpty(user_name)) {
                Toast.makeText(mContext, "姓名不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (address == null && TextUtils.isEmpty(address)) {
                Toast.makeText(mContext, "地址不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            editUserName.setVisibility(View.GONE);
            editAddress.setVisibility(View.GONE);
            textUserName.setVisibility(View.VISIBLE);
            textAddress.setVisibility(View.VISIBLE);
            setRightText("编辑");
            UserInfoModel infoModel = MyApplication.getUserInfoModel();
            String sex = "0";
            if (infoModel != null) {
                infoModel.setName(user_name);
                infoModel.setRegion(address);
                textUserName.setText(user_name);
                textAddress.setText(address);
                if (radioMan.isChecked()) {
                    sex = "1";
                    infoModel.setSex(1);
                } else {
                    sex = "2";
                    infoModel.setSex(2);
                }
            }
            WebService.getUserUpdate(mContext, user_name, address, ConstantUtils.baseUrl+infoModel.getUrl(), sex);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("个人信息", R.layout.activity_user_info);
        ButterKnife.bind(this);
        setMiddleText("个人信息");
        setRightTextVisibility(true);
        setRightText("编辑");
        UserInfoModel infoModel = MyApplication.getUserInfoModel();
        if (infoModel != null) {
            textUserName.setText(infoModel.getName());
            textAddress.setText(infoModel.getRegion());
            if (infoModel.getSex() == 1)
                radioMan.setChecked(true);
            else
                radioWoman.setChecked(true);
        }
    }
}
