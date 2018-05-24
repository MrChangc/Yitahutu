package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.FinanceDetailModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
public class FinanceDetailAdapter extends BaseAdapter {
    private List<FinanceDetailModel> financeDetailModels;
    private Context mContext;

    public FinanceDetailAdapter(List<FinanceDetailModel> financeDetailModels, Context mContext) {
        this.financeDetailModels = financeDetailModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return financeDetailModels.size();
    }

    @Override
    public Object getItem(int i) {
        return financeDetailModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        FinanceDetailModel financeDetailModel = financeDetailModels.get(i);
        if (view == null){

            view = View.inflate(mContext, R.layout.item_finance_detail_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewGroup);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (financeDetailModel!=null){
            viewHolder.textRate.setText(financeDetailModel.getRate()+"");
            viewHolder.textUnivalent.setText(financeDetailModel.getPrice()+"");
            viewHolder.textTerm.setText(financeDetailModel.getCycle()+"");
            viewHolder.textName.setText(financeDetailModel.getHeadline());
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image_finance)
        ImageView imageFinance;
        @BindView(R.id.type_id)
        TextView typeId;
        @BindView(R.id.text_rate)
        TextView textRate;
        @BindView(R.id.text_univalent)
        TextView textUnivalent;
        @BindView(R.id.text_term)
        TextView textTerm;
        @BindView(R.id.text_name)
        TextView textName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
