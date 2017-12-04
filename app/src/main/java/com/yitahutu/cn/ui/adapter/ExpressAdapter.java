package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.ExpressModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\12\1 0001.
 */
public class ExpressAdapter extends BaseAdapter {
    private List<ExpressModel.ExpressState> expressStates;
    private Context context;

    public ExpressAdapter(List<ExpressModel.ExpressState> expressStates, Context context) {
        this.expressStates = expressStates;
        this.context = context;
    }

    @Override
    public int getCount() {
        return expressStates.size();
    }

    @Override
    public Object getItem(int i) {
        return expressStates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ExpressModel.ExpressState expressState = expressStates.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_express, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.textTime.setText(expressState.getTime());
        viewHolder.textAddress.setText(expressState.getStatus());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_address)
        TextView textAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
