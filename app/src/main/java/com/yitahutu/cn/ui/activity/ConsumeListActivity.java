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
import com.yitahutu.cn.model.ConsumeModel;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.adapter.ConsumeListAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\13 0013.
 */
public class ConsumeListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.list_consume)
    ListView listConsume;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    @BindView(R.id.refresh_view)
    RefreshableView refreshView;
    private List<ConsumeModel> consumeModels = new ArrayList<>();
    private ConsumeListAdapter consumeListAdapter;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        String title = "全部";
        final int type = getIntent().getIntExtra("type", -1);
        if (type == 6)
            title = "充值/退款";
        setContentView(title, R.layout.activity_consum_list);
        ButterKnife.bind(this);
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getConsumeList(mContext, type + "",refreshView);
            }
        },getTaskId());
        initAdapter();
        listConsume.setOnItemClickListener(this);
        WebService.getConsumeList(mContext, type + "",refreshView);
    }

    private void initAdapter() {
        List<ConsumeModel> models = ConsumeModel.listAll(ConsumeModel.class);
        consumeModels.addAll(models);
        consumeListAdapter = new ConsumeListAdapter(mContext, consumeModels);
        listConsume.setAdapter(consumeListAdapter);
        setListVisibility();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.ConsumeModelEvent consumeModelEvent) {
        consumeModels.clear();
        consumeModels.addAll(consumeModelEvent.financeModels);
        consumeListAdapter.notifyDataSetChanged();
        setListVisibility();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ConsumeModel consumeModel = consumeModels.get(i);
        Intent intent = new Intent(mContext, ConsumeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("consume", consumeModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setListVisibility() {
        if (consumeModels.size() > 0) {
            refreshView.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            refreshView.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }
}
