package com.yitahutu.cn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.ui.activity.MallDetailBaseActivity;
import com.yitahutu.cn.ui.adapter.CartListDetailAdapter;
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
public class MallDetailBaseFragment extends Fragment {
    @BindView(R.id.list_cart_detail)
    ListView listCartDetail;
    Unbinder unbinder;
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
        initAdapter();
        if (state != -1)
            WebService.getCartList(state, getActivity());
        return rootView;
    }

    private void initAdapter() {
        Bundle bundle = getArguments();
        state = bundle.getInt("state");
        List<CartListModel> cartListModels = CartListModel.find(CartListModel.class, "state = ?", state + "");
        models.addAll(cartListModels);
        cartListDetailAdapter = new CartListDetailAdapter(getActivity(), models);
        listCartDetail.setAdapter(cartListDetailAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.CartListEvent listEvent) {
        if (listEvent.state == state){
            models.clear();
            models.addAll(listEvent.financeModels);
            if (cartListDetailAdapter != null)
                cartListDetailAdapter.notifyDataSetChanged();
        }
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
}
