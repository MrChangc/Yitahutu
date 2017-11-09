package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.ui.View.QdLoadingDialog;
import com.yitahutu.cn.webservice.JsonObjectCallBack;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2017\10\19 0019.
 */
public class CartListAdpater extends BaseAdapter {
    private Context mContext;
    private List<CartListModel> cartListModels;
    private List<CartListModel> models = new ArrayList<>();

    public CartListAdpater(Context mContext, List<CartListModel> cartListModels) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final CartListModel cartListModel = cartListModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_cart_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textCount.setText(cartListModel.getNum() + "");
        viewHolder.textGoodsDescribe.setText(cartListModel.getName());
        Picasso.with(mContext).load(cartListModel.getUrl()).into(viewHolder.imageGoods);
        viewHolder.checkCartList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartListModel.isCheck()) {
                    cartListModel.setCheck(false);
                    models.remove(cartListModel);
                    EventBus.getDefault().post(new Event.PriceChangeEvent());
                } else {
                    cartListModel.setCheck(true);
                    models.add(cartListModel);
                    EventBus.getDefault().post(new Event.PriceChangeEvent());
                }
            }
        });
        viewHolder.cartListDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.deleteCartList(cartListModel.getId() + "", mContext);
            }
        });
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading();
                if (cartListModel != null) {
                    final int num = cartListModel.getNum() + 1;
                    WebService.addCartList(cartListModel.getId() + "", num + "", mContext, new JsonObjectCallBack() {
                        String message = "";

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            message = "请求错误";
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            mLoadingDialog.dismiss();
                        }

                        @Override
                        public void onResponse(JSONObject response, int id) {
                            try {
                                int code = response.getInt("code");
                                if (code == 1)
                                    message = "参数为空";
                                else if (code == 300)
                                    message = "登录过期";
                                else if (code == 200) {
                                    finalViewHolder.textCount.setText(num + "");
//                                    String data = response.getString("datas");
//                                    if (data != null && !TextUtils.isEmpty(data)) {
                                    message = "添加成功";
//                                    }
                                }
                            } catch (JSONException e) {
                                message = "请求错误";
                                e.printStackTrace();
                            }
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            mLoadingDialog.dismiss();
                        }
                    });
                }
            }
        });
        viewHolder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartListModel != null) {

                    int num = cartListModel.getNum() - 1;
                    if (num <= 0)
                        Toast.makeText(mContext, "数量不能少于1", Toast.LENGTH_SHORT).show();
                    else {
                        loading();
                        WebService.addCartList(cartListModel.getId() + "", num + "", mContext, new JsonObjectCallBack() {
                            String message = "";

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                message = "请求错误";
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                mLoadingDialog.dismiss();
                            }

                            @Override
                            public void onResponse(JSONObject response, int id) {
                                try {
                                    int code = response.getInt("code");
                                    if (code == 1)
                                        message = "参数为空";
                                    else if (code == 300)
                                        message = "登录过期";
                                    else if (code == 200) {
                                        String data = response.getString("datas");
                                        if (data != null && !TextUtils.isEmpty(data)) {
                                            message = data;
                                        }
                                    }
                                } catch (JSONException e) {
                                    message = "请求错误";
                                    e.printStackTrace();
                                }
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                mLoadingDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.check_cart_list)
        CheckBox checkCartList;
        @BindView(R.id.image_goods)
        ImageView imageGoods;
        @BindView(R.id.text_goods_describe)
        TextView textGoodsDescribe;
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public List<CartListModel> getModels() {
        return models;
    }

    private QdLoadingDialog mLoadingDialog;

    private void loading() {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = new QdLoadingDialog(mContext, "加载中...");
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.show();
        }
    }
}
