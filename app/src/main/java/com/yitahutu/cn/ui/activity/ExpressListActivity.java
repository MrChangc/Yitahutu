package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.ExpressModel;
import com.yitahutu.cn.ui.adapter.ExpressAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\30 0030.
 */
public class ExpressListActivity extends BaseActivity {
    @BindView(R.id.list_express)
    ListView listExpress;
    private ExpressAdapter expressAdapter;
    private List<ExpressModel.ExpressState> expressStates = new ArrayList<>();

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("物流信息", R.layout.activity_express_list);
        ButterKnife.bind(this);
        WebService.getExpress(mContext, "70523393410955");
        initList();
    }

    private void initList() {
        expressAdapter = new ExpressAdapter(expressStates, mContext);
        listExpress.setAdapter(expressAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.ExpressModelEvent expressModelEvent){
        expressStates.clear();
        expressStates.addAll(expressModelEvent.financeModels);
        expressAdapter.notifyDataSetChanged();
    }

}
