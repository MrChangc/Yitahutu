package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.FinanceDetailModel;
import com.yitahutu.cn.ui.View.PanelDountChart2;
import com.yitahutu.cn.ui.adapter.FinanceDetailAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
public class FinanceDetailListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.text_balance)
    TextView textBalance;
    @BindView(R.id.text_investment)
    TextView textInvestment;
    @BindView(R.id.text_commission)
    TextView textCommission;
    @BindView(R.id.share_out_bonus)
    TextView shareOutBonus;
    @BindView(R.id.ll_cycle)
    LinearLayout llCycle;
    @BindView(R.id.list_finance)
    ListView listFinance;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    private List<FinanceDetailModel> financeDetailModels = new ArrayList<>();
    private FinanceDetailAdapter detailAdapter;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("理财", R.layout.activity_finance_detail_list);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initAdapter();
        listFinance.setOnItemClickListener(this);
        setCycle();
        WebService.getUserFinanceList(mContext);

    }

    private void setCycle() {
        PanelDountChart2 panelDountChart2 = new PanelDountChart2(mContext, new float[]{30, 30, 40});
        panelDountChart2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        llCycle.addView(panelDountChart2);
    }

    private void initAdapter() {
        List<FinanceDetailModel> models = FinanceDetailModel.listAll(FinanceDetailModel.class);
        financeDetailModels.addAll(models);
        detailAdapter = new FinanceDetailAdapter(financeDetailModels, mContext);
        listFinance.setAdapter(detailAdapter);
        setListVisibility();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.FinanceDetailEvent financeDetailEvent) {
        financeDetailModels.clear();
        financeDetailModels.addAll(financeDetailEvent.financeModels);
        detailAdapter.notifyDataSetChanged();
        setListVisibility();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(mContext, FinanceDetailActivity.class);
        intent.putExtra("detail_id", financeDetailModels.get(i).getId());
        startActivity(intent);
    }

    private void setListVisibility() {
        if (financeDetailModels.size() > 0) {
            listFinance.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            listFinance.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }
}
