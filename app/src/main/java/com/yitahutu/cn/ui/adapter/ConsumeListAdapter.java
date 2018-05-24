package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.ConsumeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\13 0013.
 */
public class ConsumeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ConsumeModel> consumeModels;
    private String add;
    public ConsumeListAdapter(Context context, List<ConsumeModel> consumeModels,boolean isAdd) {
        this.mContext = context;
        this.consumeModels = consumeModels;
        if (isAdd){
            add = "+";
        }else {
            add = "-";
        }
    }

    @Override
    public int getCount() {
        return consumeModels.size();
    }

    @Override
    public Object getItem(int i) {
        return consumeModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConsumeModel consumeModel = consumeModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_consume_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        if (consumeModel!=null){
            viewHolder.textYear.setText(TimeUtils.getTime3(consumeModel.getCreatTime()));
            viewHolder.textTime.setText(TimeUtils.getTime4(consumeModel.getCreatTime()));
            viewHolder.textPrice.setText(add+consumeModel.getMoney());
            viewHolder.textDescribe.setText(consumeModel.getRecord());
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_year)
        TextView textYear;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.image_consume)
        ImageView imageConsume;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.text_describe)
        TextView textDescribe;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
