package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.TotalModel;
import com.yitahutu.cn.ui.View.PanelDountChart2;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class AssetActivity extends BaseActivity {
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.text_asset_count)
    TextView textAssetCount;
    @BindView(R.id.text_balance)
    TextView textBalance;
    @BindView(R.id.text_investment)
    TextView textInvestment;
    @BindView(R.id.text_commission)
    TextView textCommission;
    @BindView(R.id.share_out_bonus)
    TextView shareOutBonus;
    @BindView(R.id.text_income)
    TextView textIncome;
    @BindView(R.id.income_detail)
    TextView incomeDetail;
    @BindView(R.id.button_recharge)
    Button buttonRecharge;
    @BindView(R.id.button_withdraw)
    Button buttonWithdraw;
    @BindView(R.id.ll_cycle)
    LinearLayout linearLayout;
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("总资产明细", R.layout.activity_asset);
        ButterKnife.bind(this);
        setMiddleText("总资产明细");
        setData();
        WebService.getUserTotalMoney(mContext);
    }

    private void setData() {
        List<TotalModel> totalModels =  TotalModel.listAll(TotalModel.class);
        if (totalModels.size()>0){
            TotalModel totalModel = totalModels.get(0);
            textIncome.setText(totalModel.getYesteday()+"");
            textBalance.setText(totalModel.getWallet()+"");
            textInvestment.setText(totalModel.getAggregate()+"");
            textCommission.setText("0.0");
            shareOutBonus.setText(totalModel.getDividend()+"");
            textIncome.setText(totalModel.getYesteday()+"");
            textAssetCount.setText(totalModel.getTotalMoney()+"");
            float[] floats = new float[]{totalModel.getWallet()/totalModel.getTotalMoney()*100,
                    totalModel.getAggregate()/totalModel.getTotalMoney()*100,
                    totalModel.getDividend()/totalModel.getTotalMoney()*100,
                    0};
            PanelDountChart2 panelDountChart2 = new PanelDountChart2(mContext,floats);
            panelDountChart2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            panelDountChart2.invalidate();
            linearLayout.removeAllViews();
            linearLayout.addView(panelDountChart2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.TotalModelEvent totalModelEvent){
        setData();
    }

}
