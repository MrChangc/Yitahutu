package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.model.RefundRecordModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\26 0026.
 */
public class RefundAdapter extends BaseAdapter {
    private Context mContext;
    private List<RefundRecordModel> refundRecordModels;

    public RefundAdapter(Context mContext, List<RefundRecordModel> refundRecordModels) {
        this.mContext = mContext;
        this.refundRecordModels = refundRecordModels;
    }

    @Override
    public int getCount() {
        return refundRecordModels.size();
    }

    @Override
    public Object getItem(int i) {
        return refundRecordModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RefundRecordModel refundRecordModel = refundRecordModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_refund_record, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (refundRecordModel!=null){
            viewHolder.textRefundName.setText(refundRecordModel.getName());
            viewHolder.textRefundDescribe.setText(refundRecordModel.getIntroduce());
            if (refundRecordModel.getState() == 6){
                viewHolder.textRefundState.setText("正在退款审核中");
            }else if (refundRecordModel.getState() == 7){
                viewHolder.textRefundState.setText("退款成功");
            }
            viewHolder.textInsuranceUnivalent.setText(refundRecordModel.getPresentPrice()+"");
            Picasso.with(mContext).load(refundRecordModel.getUrl()).into(viewHolder.cartImage);
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.cart_image)
        ImageView cartImage;
        @BindView(R.id.text_refund_name)
        TextView textRefundName;
        @BindView(R.id.text_refund_describe)
        TextView textRefundDescribe;
        @BindView(R.id.text_refund_state)
        TextView textRefundState;
        @BindView(R.id.text_insurance_univalent)
        TextView textInsuranceUnivalent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
