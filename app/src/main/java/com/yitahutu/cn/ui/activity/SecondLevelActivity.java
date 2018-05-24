package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\2\23 0023.
 */
public class SecondLevelActivity extends BaseActivity {
    @BindView(R.id.edit_evaluate)
    EditText editEvaluate;
    @BindView(R.id.ll_confirm)
    LinearLayout llConfirm;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("反馈", R.layout.activity_second_level);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_confirm)
    public void onViewClicked() {
        String back = editEvaluate.getText().toString().trim();
        if (back != null&& !TextUtils.isEmpty(back)){
            WebService.secondLevel(back, SecondLevelActivity.this, new SuccessCallBack() {
                @Override
                public void callBack() {
                    Toast.makeText(mContext, "提交成功!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void callBackToObject(Object o) {

                }
            });
        }

    }
}
