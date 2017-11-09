package com.yitahutu.cn.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.adapter.GoodsScreenAdapter;
import com.yitahutu.cn.ui.adapter.GoodsTypeAdapter;
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
 * Created by Administrator on 2017\10\31 0031.
 */
public class GoodsTypeListActivity extends Activity {
    @BindView(R.id.text_comprehensive)
    TextView textComprehensive;
    @BindView(R.id.ll_comprehensive)
    LinearLayout llComprehensive;
    @BindView(R.id.text_sales_volume)
    TextView textSalesVolume;
    @BindView(R.id.ll_sales_volume)
    LinearLayout llSalesVolume;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.text_screen)
    TextView textScreen;
    @BindView(R.id.ll_screen)
    LinearLayout llScreen;
    @BindView(R.id.type_list)
    ListView typeList;
    @BindView(R.id.image_title_left)
    ImageView imageTitleLeft;
    @BindView(R.id.image_cart)
    ImageView imageCart;
    @BindView(R.id.image_screen)
    ImageView imageScreen;
    EditText editMinPrice;
    EditText editMaxPrice;
    LinearLayout buttonConfirm;
    TextView positiveSequence;
    TextView flashback;
    private int id;
    private PopupWindow pricePopupWindow;
    private PopupWindow popupWindow;
    private List<GoodsModel> goodsModels = new ArrayList<>();
    private GoodsScreenAdapter screenAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", -1);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_type_goods_list);
        ButterKnife.bind(this);
        initPopWindow();
        comprehensive();
        initList();
    }

    private void initList() {
        List<GoodsModel> models = GoodsModel.listAll(GoodsModel.class);
        goodsModels.addAll(models);
        screenAdapter = new GoodsScreenAdapter(this,goodsModels);
    }

    private void initPopWindow() {
        View view_price = View.inflate(this, R.layout.popmenu_price, null);
        editMaxPrice = (EditText) view_price.findViewById(R.id.edit_max_price);
        editMinPrice = (EditText) view_price.findViewById(R.id.edit_min_price);
        buttonConfirm = (LinearLayout) view_price.findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceRange();
            }
        });
        pricePopupWindow = new PopupWindow(view_price, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = View.inflate(this, R.layout.popmenu_short, null);
        positiveSequence = (TextView) view.findViewById(R.id.positive_sequence);
        flashback = (TextView) view.findViewById(R.id.flashback);
        positiveSequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.getGoodsListByScreenType(GoodsTypeListActivity.this,3,id,1);
            }
        });
        flashback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.getGoodsListByScreenType(GoodsTypeListActivity.this,3,id,2);
            }
        });
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick({R.id.ll_comprehensive, R.id.ll_sales_volume,
            R.id.ll_price, R.id.ll_screen, R.id.image_title_left,
            R.id.image_cart, R.id.image_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_comprehensive:
                comprehensive();
                break;
            case R.id.ll_sales_volume:
                salesVolume();
                break;
            case R.id.ll_price:
                price();
                break;
            case R.id.ll_screen:
                screen();
                break;
            case R.id.image_title_left:
                finish();
                break;
            case R.id.image_cart:
                gotoCartList();
                break;
            case R.id.image_screen:
                gotoScreen();
                break;
        }
    }

    private void priceRange() {
        String max = editMaxPrice.getText().toString().trim();
        String mix = editMinPrice.getText().toString().trim();
        if (max!=null&&!TextUtils.isEmpty(max)&&mix!=null&&!TextUtils.isEmpty(mix)){
            WebService.getGoodsListByPriceRange(this,id,max,mix);
        }
    }

    private void screen() {
        popupWindow.showAsDropDown(llScreen);
        textPrice.setTextColor(getResources().getColor(R.color.black));
        textComprehensive.setTextColor(getResources().getColor(R.color.black));
        textSalesVolume.setTextColor(getResources().getColor(R.color.black));
        textScreen.setTextColor(getResources().getColor(R.color.title_back_ground));
    }

    private void price() {
        pricePopupWindow.showAsDropDown(llPrice);
        textPrice.setTextColor(getResources().getColor(R.color.title_back_ground));
        textComprehensive.setTextColor(getResources().getColor(R.color.black));
        textSalesVolume.setTextColor(getResources().getColor(R.color.black));
        textScreen.setTextColor(getResources().getColor(R.color.black));

    }

    private void salesVolume() {
        WebService.getGoodsListByScreenType(this, 2, id);
        textPrice.setTextColor(getResources().getColor(R.color.black));
        textComprehensive.setTextColor(getResources().getColor(R.color.black));
        textSalesVolume.setTextColor(getResources().getColor(R.color.title_back_ground));
        textScreen.setTextColor(getResources().getColor(R.color.black));
    }

    private void comprehensive() {
        WebService.getGoodsListByScreenType(this, 1, id);
        textPrice.setTextColor(getResources().getColor(R.color.black));
        textComprehensive.setTextColor(getResources().getColor(R.color.title_back_ground));
        textSalesVolume.setTextColor(getResources().getColor(R.color.black));
        textScreen.setTextColor(getResources().getColor(R.color.black));
    }

    private void gotoCartList() {
        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    private void gotoScreen() {
        Intent intent = new Intent(this, RecommendListActivity.class);
        startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.GoodsByScreenEvent goodsByScreenEvent){
        goodsModels.clear();
        goodsModels.addAll(goodsByScreenEvent.goodsModels);
        screenAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
