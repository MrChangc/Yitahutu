package com.yitahutu.cn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.ui.adapter.GoodsImageDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\24 0024.
 */
public class GoodsDetailDetailsFragment extends Fragment {
    @BindView(R.id.list_goods_detail)
    ListView listGoodsDetail;
    Unbinder unbinder;
    private View rootView;
    private GoodsImageDetailAdapter goodsImageDetailAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_goods_detail_details, null);
        unbinder = ButterKnife.bind(this, rootView);
        initAdapter();
        return rootView;
    }

    private void initAdapter() {
        GoodsDetailActivity detailActivity = (GoodsDetailActivity) getActivity();
        long id = detailActivity.getId();
        GoodsModel goodsModel = GoodsModel.findById(GoodsModel.class, id);
        List<String> strings = new ArrayList<>();

        String detailsUrl = goodsModel.getDetailsUrl();
        if (detailsUrl.contains(";")){
            String[] strings1 = detailsUrl.split(";");
            for (int i = 0; i < strings1.length; i++) {
                strings.add(ConstantUtils.baseUrl+strings1[i]);
            }
        }else {
            strings.add(ConstantUtils.baseUrl+detailsUrl);
        }
        goodsImageDetailAdapter = new GoodsImageDetailAdapter(getActivity(),strings);
        listGoodsDetail.setAdapter(goodsImageDetailAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
