package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.FinanceModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class FinanceDetailActivity extends BaseActivity {
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.text_adopt_price)
    TextView textAdoptPrice;
    @BindView(R.id.text_take_red_type)
    TextView textTakeRedType;
    @BindView(R.id.text_adopt_cycle)
    TextView textAdoptCycle;
    @BindView(R.id.text_adopt_rate)
    TextView textAdoptRate;
    @BindView(R.id.text_adopt_price_2)
    TextView textAdoptPrice2;
    @BindView(R.id.text_adopt_date)
    TextView textAdoptDate;
    @BindView(R.id.text_take_red_price)
    TextView textTakeRedPrice;
    @BindView(R.id.image_add)
    ImageView imageAdd;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.image_minus)
    ImageView imageMinus;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.checkbox_agree)
    CheckBox checkboxAgree;
    @BindView(R.id.cowboy_protocol)
    TextView cowboyProtocol;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    private FinanceModel financeModel;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra("finance_id", -1);
        financeModel = FinanceModel.findById(FinanceModel.class, id);
        String title = "未知商品";
        title = "理财第" + id + "期";
        setContentView(title, R.layout.activity_finance_detail);
        ButterKnife.bind(this);
        setData(financeModel);
    }

    private void setData(FinanceModel financeModel) {
        if (financeModel != null) {
            textAdoptPrice.setText("￥" + financeModel.getPrice() + "/只");
            textTakeRedType.setText("到期分红");
            textAdoptCycle.setText(financeModel.getCycle() + "个月");
            textAdoptRate.setText((financeModel.getRate() * 100) + "%");
            textAdoptPrice2.setText("￥" + (financeModel.getPrice() * 2) + "/只");
        }
    }

    @OnClick({R.id.image_add, R.id.image_minus, R.id.checkbox_agree, R.id.ll_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_add:
                countAdd();
                break;
            case R.id.image_minus:
                countMinus();
                break;
            case R.id.checkbox_agree:
                check();
                break;
            case R.id.ll_balance:
                balance();
                break;
        }
    }

    private void balance() {
        Intent intent = new Intent(mContext, FinanceBalanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("finance", financeModel);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void check() {

    }

    private void countMinus() {
        int count = Integer.valueOf(textCount.getText().toString());
        if (count > 1)
            textCount.setText((count - 1) + "");
        else
            Toast.makeText(mContext, "购买数量不能为空", Toast.LENGTH_SHORT).show();

    }

    private void countAdd() {
        int count = Integer.valueOf(textCount.getText().toString());
        textCount.setText((count + 1) + "");
    }
}
