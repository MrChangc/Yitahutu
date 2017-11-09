package com.yitahutu.cn.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.activity.CartListActivity;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.ui.activity.GoodsTypeListActivity;
import com.yitahutu.cn.ui.activity.RecommendListActivity;
import com.yitahutu.cn.ui.adapter.GoodsAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class MallFragment extends Fragment implements OnItemClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.image_screen)
    ImageView imageScreen;
    @BindView(R.id.image_shop_car)
    ImageView imageShopCar;
    @BindView(R.id.list_goods)
    ListView listGoods;
    @BindView(R.id.root_mall_list)
    ScrollView scrollView;
    Unbinder unbinder;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    private View rootView;
    private ConvenientBanner convenientBanner;
    private List<String> images = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    private List<GoodsModel> goodsModels = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mall, null);
        convenientBanner = (ConvenientBanner) rootView.findViewById(R.id.convenientBanner);
        if (PreferUtil.isLogin()) {
            WebService.getGoodsList(getActivity());
            WebService.getRecommendGoodsList(getActivity(), images);
        }
        //设置翻页的效果，不需要翻页效果可用不设
        //setPageTransformer(Transformer.DefaultTransformer);   // 集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        unbinder = ButterKnife.bind(this, rootView);
        scrollView.smoothScrollTo(0, 0);
        initAdapter();
        listGoods.setOnItemClickListener(this);

        return rootView;
    }

    private void initAdapter() {
        List<GoodsModel> models;
        try {
            models = GoodsModel.listAll(GoodsModel.class);
        } catch (Exception e) {
            models = new ArrayList<>();
        }
        goodsModels.clear();
        goodsModels.addAll(models);
        goodsAdapter = new GoodsAdapter(goodsModels, getActivity());
        listGoods.setAdapter(goodsAdapter);
    }


    private void loadCarouselFigure() {
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, images)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GoodsModel goodsModel = goodsModels.get(i);
        if (goodsModel != null) {
            Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", goodsModel.getId());
            getActivity().startActivity(intent);
        }
    }

    @OnClick({R.id.ll_one, R.id.ll_two, R.id.ll_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_one:
//                WebService.getGoodsListByTypeId(getActivity(), 1);
                gotoGoodsScreenActivity(1);
                break;
            case R.id.ll_two:
//                WebService.getGoodsListByTypeId(getActivity(), 2);
                gotoGoodsScreenActivity(2);
                break;
            case R.id.ll_three:
//                WebService.getGoodsListByTypeId(getActivity(), 3);
                gotoGoodsScreenActivity(3);
                break;
        }
    }

    private void gotoGoodsScreenActivity(int id) {
        Intent intent = new Intent(getActivity(), GoodsTypeListActivity.class);
        intent.putExtra("id",id);
        getActivity().startActivity(intent);
    }

    private class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
//            OkHttpGlideModule.w
            Picasso.with(getActivity())
                    .load(data)
                    .into(imageView);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initCarouselFigure(Event.RecommendGoodsEvent goodsEvent) {
        loadCarouselFigure();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.GoodsEvent goodsEvent) {
        goodsModels.clear();
        goodsModels.addAll(goodsEvent.goodsModels);
        if (goodsAdapter != null)
            goodsAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.LoginEvent loginEvent) {
        WebService.getGoodsList(getActivity());
        WebService.getRecommendGoodsList(getActivity(), images);
    }

    @OnClick(R.id.image_screen)
    public void setImageScreen() {
        Intent intent = new Intent(getActivity(), RecommendListActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.image_shop_car)
    public void setImageShopCar() {
        Intent intent = new Intent(getActivity(), CartListActivity.class);
        getActivity().startActivity(intent);
    }

}
