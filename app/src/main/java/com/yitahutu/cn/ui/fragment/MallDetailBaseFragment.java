package com.yitahutu.cn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.View.RefreshableView;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.ui.adapter.CartListDetailAdapter;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\23 0023.
 */
public class MallDetailBaseFragment extends Fragment implements SuccessCallBack {
    @BindView(R.id.list_cart_detail)
    ListView listCartDetail;
    Unbinder unbinder;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    @BindView(R.id.refresh_view)
    RefreshableView refreshView;
    private View rootView;
    private int state;
    private CartListDetailAdapter cartListDetailAdapter;
    private List<CartListModel> models = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mall_detail, null);
        unbinder = ButterKnife.bind(this, rootView);
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getCartList(state, getActivity(),MallDetailBaseFragment.this);
            }
        },getId());
        initAdapter();
        listCartDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CartListModel goodsModel = models.get(i);
                if (goodsModel != null) {
                    Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                    intent.putExtra("goods_id", goodsModel.getGoodsId());
                    getActivity().startActivity(intent);
                }
            }
        });
        if (state != -1)
            WebService.getCartList(state, getActivity(),this);
            return rootView;
    }

    private void initAdapter() {
        Bundle bundle = getArguments();
        state = bundle.getInt("state");
        List<CartListModel> cartListModels = CartListModel.find(CartListModel.class, "state = ?", state + "");
        models.addAll(cartListModels);
        cartListDetailAdapter = new CartListDetailAdapter(getActivity(), models);
        listCartDetail.setAdapter(cartListDetailAdapter);
        if (models.size() > 0) {
            listCartDetail.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
        } else {
            listCartDetail.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.CartListEvent listEvent) {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MallDetailBaseFragment newInstance(int id) {
        MallDetailBaseFragment fragmentOne = new MallDetailBaseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("state", id);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @Override
    public void callBack() {
        refreshView.finishRefreshing();
    }

    @Override
    public void callBackToObject(Object o) {
        List<CartListModel> listModels = (List<CartListModel>) o;
        models.clear();
        models.addAll(listModels);
        if (models.size() > 0) {
            refreshView.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
            if (cartListDetailAdapter != null)
                cartListDetailAdapter.notifyDataSetChanged();
        } else {
            refreshView.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
        refreshView.finishRefreshing();
    }
}
