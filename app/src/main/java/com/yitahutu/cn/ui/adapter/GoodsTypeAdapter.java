package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.RecommendModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class GoodsTypeAdapter extends BaseAdapter {
    private List<RecommendModel> recommendModels;
    private Context mContext;
    private int position = -1;
    private List<RadioButton> radioButtons = new ArrayList<>();
    public GoodsTypeAdapter(List<RecommendModel> recommendModels, Context mContext) {
        this.recommendModels = recommendModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return recommendModels.size();
    }

    @Override
    public Object getItem(int i) {
        return recommendModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_goods_type, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        radioButtons.add(viewHolder.textTypeName);
        RecommendModel recommendModel = recommendModels.get(i);
        viewHolder.textTypeName.setText(recommendModel.getName());
        if (i==0)
            viewHolder.textTypeName.setChecked(true);
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.textTypeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalViewHolder.textTypeName.isChecked()){
                    return;
                }
                if (position != -1){
                    RadioButton radioButton = radioButtons.get(position);
                    radioButton.setChecked(false);
                }
                position = i;
                finalViewHolder.textTypeName.setChecked(true);
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_type_name)
        RadioButton textTypeName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        radioButtons.clear();
    }
}
