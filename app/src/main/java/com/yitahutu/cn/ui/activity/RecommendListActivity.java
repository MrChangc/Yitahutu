package com.yitahutu.cn.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.ui.adapter.GoodsTypeAdapter;
import com.yitahutu.cn.ui.adapter.RecommendGoodsAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class RecommendListActivity extends Activity {
    @BindView(R.id.title_left_button)
    ImageView titleLeftButton;
    @BindView(R.id.title_edit)
    EditText titleEdit;
    @BindView(R.id.recommend_list_type)
    ListView recommendListType;
    @BindView(R.id.recommend_grid_goods)
    GridView recommendGridGoods;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    private GoodsTypeAdapter goodsTypeAdapter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private List<GoodsModel> goodsModels = new ArrayList<>();
    private List<RecommendModel> recommendModels = new ArrayList<>();
    private int position = 0;
    private String recommendId = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_recommend_list);
        ButterKnife.bind(this);
        initList();
        initGrid();
        titleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                WebService.getGoodsListBySerch(RecommendListActivity.this,
                        textView.getText().toString(), recommendId, "", "", "", "", "", null);
                return true;
            }
        });
        WebService.getRecommendList(this);
    }

    private void initGrid() {
        List<GoodsModel> models;
        try {
            models = GoodsModel.listAll(GoodsModel.class);

        } catch (Exception e) {
            models = new ArrayList<>();
        }
        this.goodsModels.addAll(models);
        recommendGoodsAdapter = new RecommendGoodsAdapter(goodsModels, this);
        recommendGridGoods.setAdapter(recommendGoodsAdapter);
    }

    private void initList() {
        List<RecommendModel> models = PreferUtil.getRecommendModelList();
        recommendModels.addAll(models);
        goodsTypeAdapter = new GoodsTypeAdapter(recommendModels, this);
        recommendListType.setAdapter(goodsTypeAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(Event.RecommendListEvent goodsEvent) {
        List<RecommendModel> models = PreferUtil.getRecommendModelList();
        recommendModels.clear();
        recommendModels.addAll(models);
        goodsTypeAdapter.notifyDataSetChanged();
        RecommendModel recommendModel = models.get(0);
        recommendId = recommendModel.getId() + "";
        WebService.getGoodsListByRecommend(this, recommendModel.getId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshGrid(Event.GoodsByRecommendEvent goodsEvent) {
        if (goodsEvent.goodsModels.size()>0){
            recommendGridGoods.setVisibility(View.VISIBLE);
            textNoData.setVisibility(View.GONE);
            goodsModels.clear();
            goodsModels.addAll(goodsEvent.goodsModels);
            recommendGoodsAdapter.notifyDataSetChanged();

        }else {
            recommendGridGoods.setVisibility(View.GONE);
            textNoData.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshGrid(Event.GoodsBySearch goodsEvent) {
        goodsModels.clear();
        goodsModels.addAll(goodsEvent.goodsModels);
        recommendGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
