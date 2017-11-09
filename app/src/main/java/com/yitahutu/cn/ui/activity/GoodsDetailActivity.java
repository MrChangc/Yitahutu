package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.adapter.MyFragmentPagerAdapter;
import com.yitahutu.cn.ui.fragment.EvaluateFragment;
import com.yitahutu.cn.ui.fragment.GoodsDetailDetailsFragment;
import com.yitahutu.cn.ui.fragment.GoodsDetailGoodsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\16 0016.
 */
public class GoodsDetailActivity extends FragmentActivity {
    @BindView(R.id.image_title_left)
    ImageView imageTitleLeft;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_page_mall_detail)
    ViewPager viewPageMallDetail;
    private long id;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getLongExtra("goods_id", 0);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        GoodsDetailGoodsFragment goodsDetailGoodsFragment = new GoodsDetailGoodsFragment();
        GoodsDetailDetailsFragment goodsDetailDetailsFragment = new GoodsDetailDetailsFragment();
        EvaluateFragment evaluateFragment = new EvaluateFragment();
        fragments.add(goodsDetailGoodsFragment);
        fragments.add(goodsDetailDetailsFragment);
        fragments.add(evaluateFragment);
        strings.add("商品");
        strings.add("评价");
        strings.add("详情");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, strings);
        viewPageMallDetail.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPageMallDetail);
    }

    public long getId() {
        return id;
    }

    @OnClick(R.id.image_title_left)
    public void onViewClicked() {
        finish();
    }
}
