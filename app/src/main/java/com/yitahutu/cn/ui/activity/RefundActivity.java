package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.RefundRecordModel;
import com.yitahutu.cn.ui.adapter.RefundAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\26 0026.
 */
public class RefundActivity extends BaseActivity {
    @BindView(R.id.list_refund)
    ListView listRefund;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    private RefundAdapter refundAdapter;
    private List<RefundRecordModel> refundRecordModels = new ArrayList<>();

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("退款/售后", R.layout.activity_refund);
        ButterKnife.bind(this);
        initAdapter();
        WebService.getRefundRecord(mContext);
    }

    private void initAdapter() {
        List<RefundRecordModel> models = RefundRecordModel.listAll(RefundRecordModel.class);
        refundRecordModels.addAll(models);
        refundAdapter = new RefundAdapter(mContext, refundRecordModels);
        listRefund.setAdapter(refundAdapter);
        setListVisibility();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.RefundRecordModelEvent modelEvent) {
        refundRecordModels.clear();
        refundRecordModels.addAll(modelEvent.financeModels);
        refundAdapter.notifyDataSetChanged();
        setListVisibility();
    }
    private void setListVisibility() {
        if (refundRecordModels.size() > 0) {
            listRefund.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            listRefund.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }
}
