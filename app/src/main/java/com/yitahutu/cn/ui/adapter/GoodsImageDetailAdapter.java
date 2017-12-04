package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\17 0017.
 */
public class GoodsImageDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> strings;

    public GoodsImageDetailAdapter(Context mContext, List<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_goods_detail_image, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        Picasso.with(mContext).load(strings.get(i)).into(viewHolder.imageGoodsBg);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image_goods_bg)
        ImageView imageGoodsBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
