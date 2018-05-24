package com.yitahutu.cn.ui.activity;

import android.content.Context;
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
import com.yitahutu.cn.ui.View.QdLoadingDialog;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.adapter.ConsumeListAdapter;
import com.yitahutu.cn.webservice.ErrorCallBack;
import com.yitahutu.cn.webservice.SuccessCallBack;
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
public class ConsumeListActivity extends BaseActivity implements AdapterView.OnItemClickListener, SuccessCallBack, ErrorCallBack {
    @BindView(R.id.list_consume)
    ListView listConsume;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    @BindView(R.id.refresh_view)
    RefreshableView refreshView;
    private List<ConsumeModel> consumeModels = new ArrayList<>();
    private ConsumeListAdapter consumeListAdapter;
    private int type;
    private QdLoadingDialog mLoadingDialog;
    private  void loading(Context mContext) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = new QdLoadingDialog(mContext, "加载中...");
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.show();
        }
    }
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = "全部";
        type = getIntent().getIntExtra("type", -1);
        if (type == 2)
            title = "充值/退款";
        setContentView(title, R.layout.activity_consum_list);
        ButterKnife.bind(this);
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getConsumeList(mContext, type + "", ConsumeListActivity.this, ConsumeListActivity.this);
            }
        }, getTaskId());
        initAdapter();
        listConsume.setOnItemClickListener(this);
        loading(mContext);
        WebService.getConsumeList(mContext, type + "", this, this);
    }

    private void initAdapter() {
        List<ConsumeModel> models = ConsumeModel.listAll(ConsumeModel.class);
        consumeModels.addAll(models);
        consumeListAdapter = new ConsumeListAdapter(mContext, consumeModels, type!=6);
        listConsume.setAdapter(consumeListAdapter);
        setListVisibility();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void refresh(Event.ConsumeModelEvent consumeModelEvent) {
//        consumeModels.clear();
//        consumeModels.addAll(consumeModelEvent.financeModels);
//        consumeListAdapter.notifyDataSetChanged();
//        setListVisibility();
//    }

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
            listConsume.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            listConsume.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void callBack() {

    }

    @Override
    public void callBackToObject(Object o) {
        List<ConsumeModel> models = (List<ConsumeModel>) o;
        consumeModels.clear();
        consumeModels.addAll(models);
        consumeListAdapter.notifyDataSetChanged();
        setListVisibility();
        refreshView.finishRefreshing();
        mLoadingDialog.dismiss();
    }

    @Override
    public void errorCallBack() {
        refreshView.finishRefreshing();
        mLoadingDialog.dismiss();
    }
}
