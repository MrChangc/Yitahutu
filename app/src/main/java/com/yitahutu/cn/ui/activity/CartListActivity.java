package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.adapter.CartListAdpater;
import com.yitahutu.cn.webservice.SuccessCallBack;
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
public class CartListActivity extends BaseActivity implements SuccessCallBack {
    @BindView(R.id.list_cart_goods)
    ListView listCartGoods;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.payment)
    LinearLayout payment;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    @BindView(R.id.refresh_view)
    RefreshableView refreshView;
    private CartListAdpater listAdpater;
    private List<CartListModel> cartListModels = new ArrayList<>();

    @Override
    void setRightIconListener() {
        WebService.getCartList(0, mContext,this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("购物车", R.layout.activity_cart_list);
        ButterKnife.bind(this);
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getCartList(0, mContext,CartListActivity.this);
            }
        },getTaskId());
        initList();
        setRightIconVisibility(true);
        WebService.getCartList(0, mContext,this);
    }

    private void initList() {
        List<CartListModel> models = new ArrayList<>();
        cartListModels.addAll(models);
        listAdpater = new CartListAdpater(mContext, cartListModels);
        listCartGoods.setAdapter(listAdpater);
        if (models.size() > 0) {
            textNoData.setVisibility(View.GONE);
            listCartGoods.setVisibility(View.VISIBLE);
        } else {
            textNoData.setVisibility(View.VISIBLE);
            listCartGoods.setVisibility(View.GONE);
        }
        mContext.getResources().getColor(R.color.title_back_ground);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.CartListEvent listEvent) {

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
        ArrayList<CartListModel> cartListModels = listAdpater.getModels();
        if (cartListModels != null && cartListModels.size() > 0) {
            String cartList = "";
            for (int i = 0; i < cartListModels.size(); i++) {
                if (i == 0)
                    cartList = cartListModels.get(i).getId() + "";
                else
                    cartList = cartList + "," + cartListModels.get(i).getId();
            }
            Intent intent = new Intent(mContext, MallBalanceActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", cartListModels);
            intent.putExtras(bundle);
            intent.putExtra("id_list", cartList);
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "未选择商品!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void callBack() {
        refreshView.finishRefreshing();
    }

    @Override
    public void callBackToObject(Object o) {
        List<CartListModel> models = (List<CartListModel>) o;
        cartListModels.clear();
        cartListModels.addAll(models);
        listAdpater.notifyDataSetChanged();
        if (cartListModels.size() > 0) {
            textNoData.setVisibility(View.GONE);
            listCartGoods.setVisibility(View.VISIBLE);
        } else {
            textNoData.setVisibility(View.VISIBLE);
            listCartGoods.setVisibility(View.GONE);
        }
        refreshView.finishRefreshing();

    }
}
