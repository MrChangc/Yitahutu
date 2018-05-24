package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Base64;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.adapter.MyFragmentPagerAdapter;
import com.yitahutu.cn.ui.fragment.MallDetailBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class MallDetailBaseActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_page_mall_detail)
    ViewPager viewPageMallDetail;
    private List<String> stringList = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int page = getIntent().getIntExtra("page", -1);
        setContentView("我的订单", R.layout.activity_mall_detail_base);
        ButterKnife.bind(this);
        initAdapter();
        if (page != -1)
            viewPageMallDetail.setCurrentItem(page);
    }

    private void initAdapter() {
        stringList.add("全部");
        stringList.add("待付款");
        stringList.add("待发货");
        stringList.add("待收货");
        stringList.add("待评价");
        for (int i = 0; i < stringList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(stringList.get(i)));
            if (i == 0){
                MallDetailBaseFragment mallDetailBaseFragment = MallDetailBaseFragment.newInstance(-2);
                fragments.add(mallDetailBaseFragment);
                tabLayout.addTab(tabLayout.newTab().setText(stringList.get(i)));
            }else {
                MallDetailBaseFragment mallDetailBaseFragment = MallDetailBaseFragment.newInstance(i);
                fragments.add(mallDetailBaseFragment);
                tabLayout.addTab(tabLayout.newTab().setText(stringList.get(i)));
            }
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, stringList);
        viewPageMallDetail.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPageMallDetail);
    }
}
