package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.ui.View.MyChatView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class FinanceAdapter extends BaseAdapter {
    private List<FinanceModel> financeModels;
    private Context mContext;

    public FinanceAdapter(List<FinanceModel> financeModels, Context mContext) {
        this.financeModels = financeModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return financeModels.size();
    }

    @Override
    public Object getItem(int i) {
        return financeModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FinanceModel financeModel = financeModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_finance, null);
            viewHolder = new ViewHolder(view,financeModel.getRate());
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textId.setText("第"+financeModel.getId()+"期");
        viewHolder.textRate.setText(financeModel.getRate()+"");
        viewHolder.textUnivalent.setText(financeModel.getPrice()+"");
        viewHolder.textTerm.setText(financeModel.getCycle()+"");
        MyChatView myChatView = new MyChatView(mContext,financeModel.getRate());
        myChatView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewHolder.cycleView.addView(myChatView);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_rate)
        TextView textRate;
        @BindView(R.id.text_id)
        TextView textId;
        @BindView(R.id.text_univalent)
        TextView textUnivalent;
        @BindView(R.id.text_term)
        TextView textTerm;
        @BindView(R.id.cycle_view)
        LinearLayout cycleView;

        ViewHolder(View view,double rate) {
            ButterKnife.bind(this, view);
        }
    }
}
