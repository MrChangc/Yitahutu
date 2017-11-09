package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.ui.adapter.FinanceAdapter;
import com.yitahutu.cn.webservice.WebService;

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
    private FinanceAdapter financeAdapter ;
    private List<FinanceModel> financeModels = new ArrayList<>();
    @Override
    void setRightIconListener() {
        WebService.getFinanceList(mContext);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("理财", R.layout.activity_financing_pasture);
        ButterKnife.bind(this);
        initAdapter();
        WebService.getFinanceList(mContext);
    }

    private void initAdapter() {
        List<FinanceModel> models = FinanceModel.listAll(FinanceModel.class);
        financeModels.addAll(models);
        financeAdapter = new FinanceAdapter(financeModels,mContext);
        financingPastureList.setAdapter(financeAdapter);
        financingPastureList.setOnItemClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.FinanceEvent financeEvent){
        financeModels.clear();
        financeModels.addAll(financeEvent.financeModels);
        financeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FinanceModel financeModel = financeModels.get(i);
        long id = financeModel.getId();
        Intent intent= new Intent(mContext,FinanceDetailActivity.class);
        intent.putExtra("finance_id",id);
        startActivity(intent);
    }
}
