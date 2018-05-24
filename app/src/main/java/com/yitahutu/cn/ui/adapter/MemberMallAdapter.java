package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.model.GoodsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\1\4 0004.
 */
public class MemberMallAdapter extends BaseAdapter {
    private List<GoodsModel> goodsModels;
    private Context mContext;

    public MemberMallAdapter(List<GoodsModel> goodsModels, Context mContext) {
        this.goodsModels = goodsModels;
        this.mContext = mContext;
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
        GoodsModel goodsModel = goodsModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_member_mall, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvPrice.setText("￥ " + goodsModel.getOriginalPrice());
        viewHolder.tvPriceTwo.setText("￥ " + goodsModel.getPresentPrice());
        viewHolder.tvTitle.setText(goodsModel.getIntroduce());
        Picasso.with(mContext).load(ConstantUtils.baseUrl+goodsModel.getCoverUrl()).into(viewHolder.ivMemberMall);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_member_mall)
        ImageView ivMemberMall;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_price_two)
        TextView tvPriceTwo;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
