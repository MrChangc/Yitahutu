package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.activity.MallBalanceActivity;
import com.yitahutu.cn.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\16 0016.
 */
public class GoodsAdapter extends BaseAdapter {

    private List<GoodsModel> goodsModels;

    private Context mContext;

    public GoodsAdapter(List<GoodsModel> goodsModels, Context mContext) {
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
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.goods_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final GoodsModel goodsModel = goodsModels.get(i);
        viewHolder.textName.setText(goodsModel.getName());
        viewHolder.textPrice.setText(goodsModel.getOriginalPrice() + "");
        viewHolder.textSlogan.setText(goodsModel.getName());
        viewHolder.textSloganBottom.setText(goodsModel.getIntroduce());
        viewHolder.imageAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferUtil.isLogin())
                    WebService.addGoodsToCart(goodsModel.getId() + "", 1 + "", mContext);
                else
                    Toast.makeText(mContext, "请先登录!", Toast.LENGTH_SHORT);
            }
        });
//        Picasso.with(mContext).load(ConstantUtils.baseUrl+goodsModel.getCoverUrl()).into(viewHolder.goodsImageSmall);
        Picasso.with(mContext).load(ConstantUtils.baseUrl + goodsModel.getCoverUrl()).into(viewHolder.goodsImage);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_slogan)
        TextView textSlogan;
        @BindView(R.id.text_slogan_bottom)
        TextView textSloganBottom;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.image_add_cart)
        ImageView imageAddCart;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
