package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.ConsumeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\13 0013.
 */
public class ConsumeDetailActivity extends BaseActivity {
    @BindView(R.id.text_payment_price)
    TextView textPrice;
    @BindView(R.id.text_payment_method)
    TextView textPaymentMethod;
    @BindView(R.id.text_describe)
    TextView textDescribe;
    @BindView(R.id.text_consume_time)
    TextView textConsumeTime;
    @BindView(R.id.text_payment_num)
    TextView textPaymentNum;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("明细", R.layout.activity_consume_detail);
        ButterKnife.bind(this);
        ConsumeModel consumeModel = (ConsumeModel) getIntent().getExtras().getSerializable("consume");
        if (consumeModel!=null){
            setDate(consumeModel);
        }
    }

    private void setDate(ConsumeModel consumeModel) {
        textConsumeTime.setText(TimeUtils.getTime5(consumeModel.getCreatTime()));
        textDescribe.setText(consumeModel.getRecord());
        textPaymentMethod.setText(consumeModel.getPaymentMethod());
        textPrice.setText(consumeModel.getMoney()+"");
        textPaymentNum.setText(consumeModel.getPaymentNum());
    }
}
