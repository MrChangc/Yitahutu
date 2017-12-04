package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.activity.FinanceBalanceActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\31 0031.
 */
public class GoodsScreenAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsModel> goodsModels;

    public GoodsScreenAdapter(Context mContext, List<GoodsModel> goodsModels) {
        this.mContext = mContext;
        this.goodsModels = goodsModels;
    }

    @Override
    public int getCount() {
        return goodsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return goodsModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final GoodsModel goodsModel = goodsModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_goods_screen, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.textName.setText(goodsModel.getName());
        if (goodsModel.getMonthSales() != null && !TextUtils.isEmpty(goodsModel.getMonthSales()))
            viewHolder.textSalesVolume.setText(goodsModel.getName());
        else
            viewHolder.textSalesVolume.setText("0");
        Picasso.with(mContext).load(ConstantUtils.baseUrl+goodsModel.getCoverUrl()).into(viewHolder.goodsImage);
        viewHolder.textPrice.setText(goodsModel.getPresentPrice()+"");
        viewHolder.textIntroduce.setText(goodsModel.getIntroduce());
        viewHolder.imageAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FinanceBalanceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods",goodsModel);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_sales_volume)
        TextView textSalesVolume;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.text_introduce)
        TextView textIntroduce;
        @BindView(R.id.image_add_cart)
        ImageView imageAddCart;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
