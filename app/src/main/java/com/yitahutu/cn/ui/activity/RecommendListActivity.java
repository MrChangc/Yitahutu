package com.yitahutu.cn.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RecommendModel;
import com.yitahutu.cn.ui.adapter.GoodsAdapter;
import com.yitahutu.cn.ui.adapter.GoodsTypeAdapter;
import com.yitahutu.cn.ui.adapter.RecommendGoodsAdapter;
import com.yitahutu.cn.webservice.WebService;

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
    private GoodsTypeAdapter goodsTypeAdapter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private List<GoodsModel> goodsModels = new ArrayList<>();
    private List<RecommendModel> recommendModels = new ArrayList<>();
    private int position = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);
        ButterKnife.bind(this);
        initList();
        initGrid();
        WebService.getRecommendList(this);
    }

    private void initGrid() {
        List<GoodsModel> models;
        try {
           models = GoodsModel.listAll(GoodsModel.class);

        }catch (Exception e){
            models = new ArrayList<>();
        }
        this.goodsModels.addAll(models);
        recommendGoodsAdapter = new RecommendGoodsAdapter(goodsModels,this);
        recommendGridGoods.setAdapter(recommendGoodsAdapter);
    }

    private void initList() {
        List<RecommendModel> models = PreferUtil.getRecommendModelList();
        recommendModels.addAll(models);
        goodsTypeAdapter = new GoodsTypeAdapter(recommendModels,this);
        recommendListType.setAdapter(goodsTypeAdapter);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(Event.RecommendGoodsEvent goodsEvent){
        List<RecommendModel> models = PreferUtil.getRecommendModelList();
        recommendModels.clear();
        recommendModels.addAll(models);
        goodsTypeAdapter.notifyDataSetChanged();
        RecommendModel recommendModel = models.get(0);
        WebService.getGoodsListByRecommend(this,recommendModel.getId());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshGrid(Event.GoodsByRecommendEvent goodsEvent){
        goodsModels.clear();
        goodsModels.addAll(goodsEvent.goodsModels);
        recommendGoodsAdapter.notifyDataSetChanged();
    }


}
