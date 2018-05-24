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
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\1\11 0011.
 */
public class AddEvaluateActivity extends BaseActivity {
    @BindView(R.id.edit_evaluate)
    EditText editEvaluate;
    @BindView(R.id.button_evaluate_good)
    RadioButton buttonEvaluateGood;
    @BindView(R.id.button_evaluate_normal)
    RadioButton buttonEvaluateNormal;
    @BindView(R.id.button_evaluate_feedback)
    RadioButton buttonEvaluateFeedback;
    @BindView(R.id.ll_confirm)
    LinearLayout llConfirm;
    private int grade;
    private String id,goodsId;
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("添加评论", R.layout.activity_add_evaluate);
        id = getIntent().getStringExtra("id");
        goodsId = getIntent().getStringExtra("goodsId");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_evaluate_good, R.id.button_evaluate_normal, R.id.button_evaluate_feedback, R.id.ll_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_evaluate_good:
                buttonEvaluateGood.setChecked(true);
                buttonEvaluateNormal.setChecked(false);
                buttonEvaluateFeedback.setChecked(false);
                grade = 0;
                break;
            case R.id.button_evaluate_normal:
                buttonEvaluateGood.setChecked(false);
                buttonEvaluateNormal.setChecked(true);
                buttonEvaluateFeedback.setChecked(false);
                grade = 1;
                break;
            case R.id.button_evaluate_feedback:
                buttonEvaluateGood.setChecked(false);
                buttonEvaluateNormal.setChecked(false);
                buttonEvaluateFeedback.setChecked(true);
                grade = 2;
                break;
            case R.id.ll_confirm:
                if (editEvaluate.getText().toString()!=null&&!TextUtils.isEmpty(editEvaluate.getText().toString()))
                WebService.evaluationInsert(goodsId, id, grade + "", editEvaluate.getText().toString(), mContext, new SuccessCallBack() {
                    @Override
                    public void callBack() {

                    }

                    @Override
                    public void callBackToObject(Object o) {
                        Toast.makeText(mContext,"提交成功!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
        }
    }
}
