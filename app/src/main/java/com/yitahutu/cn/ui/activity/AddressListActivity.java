package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.ui.adapter.AddressListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\27 0027.
 */
public class AddressListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.list_address)
    ListView listAddress;
    private AddressListAdapter addressListAdapter;
    private List<AddressModel> addressModels;
    @BindView(R.id.ll_add_address)
    LinearLayout llAddAddress;
    @Override
    void setRightIconListener() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("选择收货地址", R.layout.activity_address_list);
        ButterKnife.bind(this);
        setRightIconVisibility(false);
        listAddress.setOnItemClickListener(this);
    }

    private void initAdapter() {
        addressModels = AddressModel.listAll(AddressModel.class);
        addressListAdapter = new AddressListAdapter(mContext,addressModels);
        listAddress.setAdapter(addressListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AddressModel addressModel = addressModels.get(i);
        long id = addressModel.getId();
        Intent intent = new Intent(mContext,AddressDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        initAdapter();
    }
    @OnClick(R.id.ll_add_address)
    public void setListAddress(){
        Intent intent = new Intent(mContext,AddressDetailActivity.class);
        startActivity(intent);
    }
}
