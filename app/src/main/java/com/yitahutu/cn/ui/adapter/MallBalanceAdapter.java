package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.ui.activity.BuySuccessActivity;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\9 0009.
 */
public class MallBalanceAdapter extends BaseAdapter {
    private Context mContext;
    private List<CartListModel> cartListModels;
    private TextView textView;

    public MallBalanceAdapter(List<CartListModel> cartListModels, Context mContext,TextView textView) {
        this.cartListModels = cartListModels;
        this.mContext = mContext;
        this.textView = textView;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final CartListModel cartListModel = cartListModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_mall_balance, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        if (cartListModel!=null){
            viewHolder.textGoodsName.setText(cartListModel.getName());
            viewHolder.textGoodsPrice.setText(cartListModel.getPresentPrice()+"");
            viewHolder.textCount.setText(cartListModel.getNum()+"");
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.imageAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int addCount = cartListModel.getNum();
                    finalViewHolder.textCount.setText((addCount + 1) + "");
                }
            });
            viewHolder.imageMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = cartListModel.getNum();
                    if (count <= 1)
                        Toast.makeText(mContext, "个数不能为零", Toast.LENGTH_SHORT).show();
                    else
                        finalViewHolder.textCount.setText((count - 1) + "");
                }
            });
            viewHolder.cartListDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    WebService.deleteCartList(cartListModel.getId() + "", mContext, new SuccessCallBack() {
//                        @Override
//                        public void callBack() {
//                            cartListModels.remove(cartListModel);
//                            notifyDataSetChanged();
//                        }
//                    });
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image_goods)
        ImageView imageGoods;
        @BindView(R.id.text_goods_name)
        TextView textGoodsName;
        @BindView(R.id.text_goods_price)
        TextView textGoodsPrice;
        @BindView(R.id.image_add)
        ImageView imageAdd;
        @BindView(R.id.text_count)
        TextView textCount;
        @BindView(R.id.image_minus)
        ImageView imageMinus;
        @BindView(R.id.cart_list_delete)
        ImageView cartListDelete;
        @BindView(R.id.text_integration)
        TextView textIntegration;
        @BindView(R.id.text_coupon)
        TextView textCoupon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
