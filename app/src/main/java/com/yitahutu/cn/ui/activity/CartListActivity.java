package com.yitahutu.cn.ui.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.ui.View.PaymentDialog;
import com.yitahutu.cn.ui.adapter.CartListAdpater;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\19 0019.
 */
public class CartListActivity extends BaseActivity {
    @BindView(R.id.list_cart_goods)
    ListView listCartGoods;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.payment)
    LinearLayout payment;
    private CartListAdpater listAdpater;
    private List<CartListModel> cartListModels = new ArrayList<>();

    @Override
    void setRightIconListener() {
        WebService.getCartList(0, mContext);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("购物车", R.layout.activity_cart_list);
        ButterKnife.bind(this);
        initList();
        WebService.getCartList(0, mContext);
    }

    private void initList() {
        List<CartListModel> models = new ArrayList<>();
        cartListModels.addAll(models);
        listAdpater = new CartListAdpater(mContext, cartListModels);
        listCartGoods.setAdapter(listAdpater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.CartListEvent listEvent) {
        cartListModels.clear();
        cartListModels.addAll(listEvent.financeModels);
        listAdpater.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.PriceChangeEvent changeEvent) {
        if (listAdpater != null) {
            List<CartListModel> cartListModels = listAdpater.getModels();
            double total = 0;
            for (CartListModel cartListModel : cartListModels) {
                if (cartListModel != null) {
                    total = total + cartListModel.getNum() * cartListModel.getPresentPrice();
                }
            }
            textTotal.setText("￥ " + total);
        }
    }

    @OnClick(R.id.payment)
    public void setPayment() {

    }

}
