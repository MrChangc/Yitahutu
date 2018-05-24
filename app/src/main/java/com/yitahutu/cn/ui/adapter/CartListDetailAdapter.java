package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.ExpressModel;
import com.yitahutu.cn.ui.activity.AddEvaluateActivity;
import com.yitahutu.cn.ui.activity.ExpressListActivity;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\23 0023.
 */
public class CartListDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<CartListModel> cartListModels;

    public CartListDetailAdapter(Context mContext, List<CartListModel> cartListModels) {
        this.mContext = mContext;
        this.cartListModels = cartListModels;
    }

    @Override
    public int getCount() {
        return cartListModels.size();
    }

    @Override
    public Object getItem(int i) {
        return cartListModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final CartListModel cartListModel = cartListModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_cart_list_detail, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (cartListModel != null) {
            viewHolder.textCartUnivalent.setText("￥ "+cartListModel.getPresentPrice());
            viewHolder.textCartDescribe.setText(cartListModel.getName());
            viewHolder.textCartTaste.setText(cartListModel.getIntroduce());
            viewHolder.textCartCount.setText(cartListModel.getNum()+"");
            viewHolder.textCartSum.setText("共计" + cartListModel.getNum() + "件商品");
            viewHolder.textCartTotal.setText("共计￥ " + (cartListModel.getNum() * cartListModel.getPresentPrice()+cartListModel.getFreight()));
            Picasso.with(mContext).load(ConstantUtils.baseUrl+cartListModel.getUrl()).into(viewHolder.cartImage);
//
            final int state = cartListModel.getState();
            if (state == 1) {
                viewHolder.radioLeft.setVisibility(View.VISIBLE);
                viewHolder.radioLeft.setText("联系卖家");
                viewHolder.radioMiddle.setVisibility(View.VISIBLE);
                viewHolder.radioMiddle.setText("取消订单");
                viewHolder.radioRight.setVisibility(View.VISIBLE);
                viewHolder.radioRight.setText("付款");
            } else if (state == 2) {
                viewHolder.radioLeft.setVisibility(View.GONE);
                viewHolder.radioLeft.setText("联系卖家");
                viewHolder.radioMiddle.setVisibility(View.GONE);
                viewHolder.radioMiddle.setText("取消订单");
                viewHolder.radioRight.setVisibility(View.VISIBLE);
                viewHolder.radioRight.setText("提醒发货");
            } else if (state == 3) {
                viewHolder.radioLeft.setVisibility(View.VISIBLE);
                viewHolder.radioLeft.setText("延长收货");
                viewHolder.radioMiddle.setVisibility(View.VISIBLE);
                viewHolder.radioMiddle.setText("查看物流");
                viewHolder.radioRight.setVisibility(View.VISIBLE);
                viewHolder.radioRight.setText("确认收货");
            } else if (state == 4) {
                viewHolder.radioLeft.setVisibility(View.VISIBLE);
                viewHolder.radioLeft.setText("删除订单");
                viewHolder.radioMiddle.setVisibility(View.VISIBLE);
                viewHolder.radioMiddle.setText("查看物流");
                viewHolder.radioRight.setVisibility(View.VISIBLE);
                viewHolder.radioRight.setText("评价");
            }
            viewHolder.radioLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (state == 1){
//                        联系卖家
                    }else if (state == 2){
//
                    }else if (state == 3){
//                        延长收货
                    }else if (state == 4){
                        //删除叮订单
                    }
                }
            });
            viewHolder.radioMiddle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (state == 1){
//                        取消订单
                    }else if (state == 2){
//
                    }else if (state == 3){
//                        查看物流
                        lookExpress(cartListModel);
                    }else if (state == 4){
                        //查看物流
                        lookExpress(cartListModel);
                    }
                }
            });
            viewHolder.radioRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (state == 1){
//                        付款
                    }else if (state == 2){
//                        提醒发货
                        Toast.makeText(mContext,"以提醒发货!",Toast.LENGTH_SHORT).show();
                    }else if (state == 3){
//                        确认收货
                    }else if (state == 4){
                        //评价
                        int goodsId = cartListModel.getGoodsId();
                        long id = cartListModel.getId();
                        Intent intent = new Intent(mContext, AddEvaluateActivity.class);
                        intent.putExtra("goodsId",goodsId+"");
                        intent.putExtra("id",id+"");
                        mContext.startActivity(intent);

                    }
                }
            });
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CartListModel goodsModel = cartListModels.get(i);
                    if (goodsModel != null) {
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("goods_id", goodsModel.getGoodsId());
                        mContext.startActivity(intent);
                    }
                }
            });
        }
        return view;
    }

    private void lookExpress(CartListModel cartListModel) {
        Intent intent = new Intent(mContext, ExpressListActivity.class);
        intent.putExtra("number",cartListModel.getTransNumber());
        mContext.startActivity(intent);
    }


    static class ViewHolder {
        @BindView(R.id.cart_image)
        ImageView cartImage;
        @BindView(R.id.text_cart_describe)
        TextView textCartDescribe;
        @BindView(R.id.text_cart_taste)
        TextView textCartTaste;
        @BindView(R.id.text_cart_univalent)
        TextView textCartUnivalent;
        @BindView(R.id.text_cart_count)
        TextView textCartCount;
        @BindView(R.id.text_insurance_univalent)
        TextView textInsuranceUnivalent;
        @BindView(R.id.text_insurance_count)
        TextView textInsuranceCount;
        @BindView(R.id.text_cart_sum)
        TextView textCartSum;
        @BindView(R.id.text_cart_total)
        TextView textCartTotal;
        @BindView(R.id.radio_left)
        RadioButton radioLeft;
        @BindView(R.id.radio_middle)
        RadioButton radioMiddle;
        @BindView(R.id.radio_right)
        RadioButton radioRight;
        @BindView(R.id.ll_cart_list_detail)
        LinearLayout linearLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
