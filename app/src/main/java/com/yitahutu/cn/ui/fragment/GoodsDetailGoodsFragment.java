package com.yitahutu.cn.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.View.QdLoadingDialog;
import com.yitahutu.cn.ui.activity.CartListActivity;
import com.yitahutu.cn.ui.activity.FinanceBalanceActivity;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.webservice.JsonObjectCallBack;
import com.yitahutu.cn.webservice.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017\10\24 0024.
 */
public class GoodsDetailGoodsFragment extends Fragment implements OnItemClickListener {
    private long id = -1;
    @BindView(R.id.Convenient_banner_goods_detail)
    ConvenientBanner ConvenientBannerGoodsDetail;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_synopsis)
    TextView textSynopsis;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.text_price_favourable)
    TextView textPriceFavourable;
    @BindView(R.id.image_add)
    ImageView imageAdd;
    @BindView(R.id.text_goods_count)
    TextView textGoodsCount;
    @BindView(R.id.image_minus)
    ImageView imageMinus;
    @BindView(R.id.text_goods_total)
    TextView textGoodsTotal;
    @BindView(R.id.ll_add_cart)
    LinearLayout llAddCart;
    @BindView(R.id.ll_buying)
    LinearLayout llBuying;
    @BindView(R.id.payment)
    LinearLayout payment;
    Unbinder unbinder;
    private View rootView;
    private List<String> images = new ArrayList<>();
    private GoodsModel goodsModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoodsDetailActivity detailActivity = (GoodsDetailActivity) getActivity();
        id = detailActivity.getId();
        goodsModel = GoodsModel.findById(GoodsModel.class, id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_goods_detail_goods, null);
        unbinder = ButterKnife.bind(this, rootView);
        setData();
        loadCarouselFigure();
        return rootView;
    }

    private void setData() {
        if (goodsModel != null) {
            textName.setText(goodsModel.getName());
            textPrice.setText(goodsModel.getPresentPrice() + "");
            textPriceFavourable.setText(goodsModel.getOriginalPrice() + "");
            textSynopsis.setText(goodsModel.getIntroduce());
            textGoodsTotal.setText("￥ " + (goodsModel.getPresentPrice() * Integer.valueOf(textGoodsCount.getText().toString())));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_add, R.id.image_minus, R.id.ll_add_cart, R.id.ll_buying, R.id.payment})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.image_add:
                int addCount = Integer.valueOf(textGoodsCount.getText().length());
                textGoodsCount.setText((addCount + 1)+"");
                break;
            case R.id.image_minus:
                int count = Integer.valueOf(textGoodsCount.getText().toString());
                if (count <= 1)
                    Toast.makeText(getActivity(), "个数不能为零", Toast.LENGTH_SHORT).show();
                else
                    textGoodsCount.setText((count - 1)+"");
                break;
            case R.id.ll_add_cart:
                if (PreferUtil.isLogin())
                    llAddCart();
                else
                    Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_buying:
                if (PreferUtil.isLogin())
                    gotoBalance();
                else
                    Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.payment:
                Intent intent  = new Intent(getActivity(), CartListActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    private void llAddCart() {
        loading();
        WebService.addGoodsToCart(goodsModel.getId() + "", textGoodsCount.getText().toString(), new JsonObjectCallBack() {
            String message = "";

            @Override
            public void onError(Call call, Exception e, int id) {
                message = "请求错误";
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                mLoadingDialog.dismiss();
            }

            @Override
            public void onResponse(JSONObject response, int id) {
                try {
                    int code = response.getInt("code");
                    if (code == 1)
                        message = "参数为空";
                    else if (code == 300)
                        message = "登录过期";
                    else if (code == 200) {
//                                String data = response.getString("datas");
//                                if (data != null && !TextUtils.isEmpty(data)) {
                        message = "加入购物车成功!";
//                                }
                    }
                } catch (JSONException e) {
                    message = "请求错误";
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                mLoadingDialog.dismiss();
            }
        });
    }

    private void gotoBalance() {
        Intent intent = new Intent(getActivity(), FinanceBalanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",goodsModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private QdLoadingDialog mLoadingDialog;

    private void loading() {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = new QdLoadingDialog(getActivity(), "加载中...");
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.show();
        }
    }

    private void loadCarouselFigure() {
        String allUrl = goodsModel.getUrl();
        images.clear();
        if (allUrl.contains(";")) {
            String[] strings = allUrl.split(";");
            for (int i = 0; i < strings.length; i++) {
                images.add(ConstantUtils.baseUrl+strings[i]);
            }
        } else {
            images.add(allUrl);
        }
        //开始自动翻页
        ConvenientBannerGoodsDetail.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, images)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
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
            Picasso.with(getActivity())
                    .load(data)
                    .into(imageView);
        }
    }

}
