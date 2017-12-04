package com.yitahutu.cn.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.FinanceDetailModel;
import com.yitahutu.cn.model.FinanceModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class FinanceDetailActivity extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.text_adopt_price)
    TextView textAdoptPrice;
    @BindView(R.id.text_take_red_type)
    TextView textTakeRedType;
    @BindView(R.id.text_adopt_cycle)
    TextView textAdoptCycle;
    @BindView(R.id.text_adopt_rate)
    TextView textAdoptRate;
    @BindView(R.id.text_adopt_price_2)
    TextView textAdoptPrice2;
    @BindView(R.id.text_adopt_date)
    TextView textAdoptDate;
    @BindView(R.id.text_take_red_price)
    TextView textTakeRedPrice;
    @BindView(R.id.image_add)
    ImageView imageAdd;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.image_minus)
    ImageView imageMinus;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.checkbox_agree)
    CheckBox checkboxAgree;
    @BindView(R.id.cowboy_protocol)
    TextView cowboyProtocol;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.ll_financing_info)
    LinearLayout llFinancingInfo;
    @BindView(R.id.text_no_data)
    TextView textNoData;
    private FinanceModel financeModel;
    private FinanceDetailModel financeDetailModel;
    private ConvenientBanner convenientBanner;
    private List<String> images = new ArrayList<>();

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra("finance_id", -1);
        long detail_id = getIntent().getLongExtra("detail_id", -1);
        if (detail_id != -1)
            financeDetailModel = FinanceDetailModel.findById(FinanceDetailModel.class, detail_id);
        else if (id != -1)
            financeModel = FinanceModel.findById(FinanceModel.class, id);
        String title = "未知商品";
        title = "理财第" + id + "期";
        setContentView(title, R.layout.activity_finance_detail);
        ButterKnife.bind(this);
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        loadCarouselFigure();
        if (PreferUtil.isNetWork()) {
            textNoData.setVisibility(View.GONE);
            llFinancingInfo.setVisibility(View.VISIBLE);
            setData(financeModel);
        } else {
            textNoData.setVisibility(View.VISIBLE);
            llFinancingInfo.setVisibility(View.GONE);
        }


    }

    private void setData(FinanceModel financeModel) {
        if (financeModel != null) {
            textAdoptPrice.setText("￥" + financeModel.getPrice() + "/只");
            textTakeRedType.setText("到期分红");
            textAdoptCycle.setText(financeModel.getCycle() + "个月");
            textAdoptRate.setText((financeModel.getRate() * 100) + "%");
            textAdoptPrice2.setText("￥" + (financeModel.getPrice() * 2) + "/只");
            textCount.setText("1");
            textTotal.setText(financeModel.getPrice() + "");
        }
        if (financeDetailModel != null) {
            textAdoptPrice.setText("￥" + financeDetailModel.getPrice() + "/只");
            textTakeRedType.setText("到期分红");
            textAdoptCycle.setText(financeDetailModel.getCycle() + "个月");
            textAdoptRate.setText((financeDetailModel.getRate() * 100) + "%");
            textAdoptPrice2.setText("￥" + (financeDetailModel.getPrice() * 2) + "/只");
        }
    }

    @OnClick({R.id.image_add, R.id.image_minus, R.id.checkbox_agree, R.id.ll_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_add:
                countAdd();
                break;
            case R.id.image_minus:
                countMinus();
                break;
            case R.id.checkbox_agree:
                check();
                break;
            case R.id.ll_balance:
                balance();
                break;
        }
    }

    private void balance() {
        if (checkboxAgree.isChecked()){

            if (PreferUtil.isLogin()){
                Intent intent = new Intent(mContext, FinanceBalanceActivity.class);
                Bundle bundle = new Bundle();
                if (financeModel != null)
                    bundle.putSerializable("finance", financeModel);
                else if (financeDetailModel != null)
                    bundle.putSerializable("finance", financeDetailModel);
                intent.putExtras(bundle);
                intent.putExtra("number",Integer.valueOf(textCount.getText().toString()));
                startActivity(intent);
            }else {
                Toast.makeText(mContext,"请先登录!",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContext,"请勾选协议!",Toast.LENGTH_SHORT).show();
        }

    }

    private void check() {

    }

    private void countMinus() {
        int count = Integer.valueOf(textCount.getText().toString());
        if (count > 1) {
            textCount.setText((count - 1) + "");
            if (financeModel != null)
                textTotal.setText((count - 1) * financeModel.getPrice() + "");
            else if (financeDetailModel != null)
                textTotal.setText((count - 1) * financeDetailModel.getPrice() + "");
        } else
            Toast.makeText(mContext, "购买数量不能为空", Toast.LENGTH_SHORT).show();

    }

    private void countAdd() {
        int count = Integer.valueOf(textCount.getText().toString());
        textCount.setText((count + 1) + "");
        if (financeModel != null)
            textTotal.setText((count + 1) * financeModel.getPrice() + "");
        else if (financeDetailModel != null)
            textTotal.setText((count + 1) * financeDetailModel.getPrice() + "");
    }

    private void loadCarouselFigure() {
        String allUrl = null;
        if (financeModel != null)
            allUrl = financeModel.getUrl();
        else if (financeDetailModel != null)
            allUrl = financeDetailModel.getUrl();
        images.clear();
        if (allUrl.contains(";")) {
            String[] strings = allUrl.split(";");
            for (int i = 0; i < strings.length; i++) {
                images.add(ConstantUtils.baseUrl + strings[i]);
            }
        } else {
            images.add(allUrl);
        }
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
            if (images.size() <= 0) {
                imageView.setImageResource(R.mipmap.defult_convenient_banner);
                return;
            }
            Picasso.with(mContext)
                    .load(data)
                    .into(imageView);
        }
    }
}
