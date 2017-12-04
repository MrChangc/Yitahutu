package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.adapter.FinanceAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class FinancingPastureActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.financing_pasture_list)
    ListView financingPastureList;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    @BindView(R.id.refresh_view)
    RefreshableView refreshView;
    private FinanceAdapter financeAdapter;
    private List<FinanceModel> financeModels = new ArrayList<>();

    @Override
    void setRightIconListener() {
        WebService.getFinanceList(mContext,refreshView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("理财", R.layout.activity_financing_pasture);
        ButterKnife.bind(this);
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getFinanceList(mContext,refreshView);
            }
        },getTaskId());
        initAdapter();
        setRightIconVisibility(true);
        WebService.getFinanceList(mContext,refreshView);
    }

    private void initAdapter() {
        List<FinanceModel> models = FinanceModel.listAll(FinanceModel.class);
        financeModels.addAll(models);
        financeAdapter = new FinanceAdapter(financeModels, mContext);
        financingPastureList.setAdapter(financeAdapter);
        financingPastureList.setOnItemClickListener(this);
        setListVisibility();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.FinanceEvent financeEvent) {
        financeModels.clear();
        financeModels.addAll(financeEvent.financeModels);
        financeAdapter.notifyDataSetChanged();
        setListVisibility();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FinanceModel financeModel = financeModels.get(i);
        long id = financeModel.getId();
        Intent intent = new Intent(mContext, FinanceDetailActivity.class);
        intent.putExtra("finance_id", id);
        startActivity(intent);
    }

    private void setListVisibility() {
        if (financeModels.size() > 0) {
            refreshView.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            refreshView.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
