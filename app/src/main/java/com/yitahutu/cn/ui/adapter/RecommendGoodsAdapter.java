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
 * Created by Administrator on 2017\10\18 0018.
 */
public class RecommendGoodsAdapter extends BaseAdapter {
    private List<GoodsModel> goodsModels;
    private Context mContext;

    public RecommendGoodsAdapter(List<GoodsModel> goodsModels, Context mContext) {
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
        ViewHolder viewHolder= null;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_goods_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GoodsModel goodsModel = goodsModels.get(i);
        viewHolder.textGoodsName.setText(goodsModel.getName());
        Picasso.with(mContext).load(ConstantUtils.baseUrl+goodsModel.getUrl()).into(viewHolder.gridItemImage);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.grid_item_image)
        ImageView gridItemImage;
        @BindView(R.id.text_goods_name)
        TextView textGoodsName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
