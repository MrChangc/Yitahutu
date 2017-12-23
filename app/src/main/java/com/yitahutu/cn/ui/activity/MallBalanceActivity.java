package com.yitahutu.cn.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.View.PaymentDialog;
import com.yitahutu.cn.ui.adapter.MallBalanceAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\11\9 0009.
 */
public class MallBalanceActivity extends BaseActivity {
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.text_phone_number)
    TextView textPhoneNumber;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.ll_address_info)
    LinearLayout llAddressInfo;
    @BindView(R.id.list_balance)
    ListView listBalance;
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.payment)
    LinearLayout payment;
    @BindView(R.id.image_goods)
    ImageView imageGoods;
    @BindView(R.id.text_goods_name)
    TextView textGoodsName;
    @BindView(R.id.text_goods_price)
    TextView textGoodsPrice;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.cart_list_delete)
    ImageView cartListDelete;
    @BindView(R.id.text_integration)
    TextView textIntegration;
    @BindView(R.id.text_coupon)
    TextView textCoupon;
    @BindView(R.id.image_add)
    ImageView imageAdd;
    @BindView(R.id.image_minus)
    ImageView imageMinus;
    @BindView(R.id.ll_goods_info)
    LinearLayout llGoodsInfo;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.text_add_new)
    TextView textAddNew;
    private MallBalanceAdapter balanceAdapter;
    private GoodsModel goodsModel = null;
    private String addressId = "-1";
    private String cartList;
    private String goodsId;
    private String idGoods;
    private int num;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("购买商品", R.layout.activity_mall_balance);
        ArrayList<CartListModel> cartListModels = (ArrayList<CartListModel>) getIntent().getExtras().getSerializable("list");
        cartList = getIntent().getStringExtra("id_list");
        goodsId = getIntent().getStringExtra("id");
        num = getIntent().getIntExtra("number",1);
        goodsModel = (GoodsModel) getIntent().getExtras().getSerializable("goods");
        ButterKnife.bind(this);
        if (cartListModels != null) {
            initAdapter(cartListModels);
        } else if (goodsModel != null) {
            setData(goodsModel);
        }
    }

    private void setData(GoodsModel goodsModel) {
        listBalance.setVisibility(View.GONE);
        llGoodsInfo.setVisibility(View.VISIBLE);
        textGoodsName.setText(goodsModel.getName());
        textGoodsPrice.setText("￥ " + goodsModel.getPresentPrice());
        textCount.setText(num+"");
        textTotal.setText(goodsModel.getPresentPrice()*num + "");
        Picasso.with(mContext).load(ConstantUtils.baseUrl + goodsModel.getCoverUrl()).into(imageGoods);
    }

    private void initAdapter(ArrayList<CartListModel> cartListModels) {
        double price = 0;
        for (int i = 0; i < cartListModels.size(); i++) {
            price = price + (cartListModels.get(i).getPresentPrice() * cartListModels.get(i).getNum());
        }
        listBalance.setVisibility(View.VISIBLE);
        llGoodsInfo.setVisibility(View.GONE);
        balanceAdapter = new MallBalanceAdapter(cartListModels, mContext, textTotal);
        listBalance.setAdapter(balanceAdapter);
        textTotal.setText(price*num + "");
    }

    @OnClick({R.id.ll_address_info, R.id.payment, R.id.image_add, R.id.image_minus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address_info:
                gotoAddressListActivity();
                break;
            case R.id.payment:
                if (addressId.equals("-1")) {
                    Toast.makeText(mContext, "请选择收货地址!", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPaymentDialog();
                break;
            case R.id.image_add:
                if (goodsModel != null) {
                    num++;
                    textCount.setText(num + "");
                }
                break;
            case R.id.image_minus:
                if (goodsModel != null) {
                    if (num > 1) {
                        num--;
                        textCount.setText(num + "");
                    } else
                        Toast.makeText(mContext, "个数不能少于1!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void gotoAddressListActivity() {
        Intent intent = new Intent(mContext, AddressListActivity.class);
        startActivityForResult(intent, 0);
    }

    private void setAddress() {
        boolean isCheck = false;
        List<AddressModel> models = AddressModel.listAll(AddressModel.class);
        for (AddressModel addressModel : models) {
            if (addressModel.isCheck()) {
                textAddress.setText(addressModel.getDetailAddress());
                textUserName.setText(addressModel.getName());
                textPhoneNumber.setText(addressModel.getPhone());
                isCheck = true;
                addressId = addressModel.getId() + "";
                break;
            }
        }
        if (!isCheck) {
            if (models.size() > 0) {
                llInfo.setVisibility(View.VISIBLE);
                textAddNew.setVisibility(View.GONE);
                AddressModel addressModel = models.get(0);
                textAddress.setText(addressModel.getDetailAddress());
                textUserName.setText(addressModel.getName());
                textPhoneNumber.setText(addressModel.getPhone());
                addressId = addressModel.getId() + "";
            } else {
                llInfo.setVisibility(View.GONE);
                textAddNew.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setAddress();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showPaymentDialog() {
//        if (MyApplication.getUserInfoModel() != null) {
        PaymentDialog paymentDialog = new PaymentDialog(mContext, new PaymentDialog.BalanceOnClick() {
            @Override
            public void onBalance() {

                if (goodsId != null && !TextUtils.isEmpty(goodsId))
                    WebService.buyGoods(addressId, goodsId, textCount.getText().toString(), "0", mContext);
                else if (cartList != null && !TextUtils.isEmpty(cartList))
                    WebService.buyCart(addressId, cartList, "0", mContext);
            }
        });
        paymentDialog.create();
        if (MyApplication.getUserInfoModel() != null) {
            paymentDialog.setTextNumber(MyApplication.getUserInfoModel().getName());
        }
        paymentDialog.setTextCount(textTotal.getText().toString());
        paymentDialog.setTextType("牛币");
        paymentDialog.show();
//        }else
//            Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void gotoBuySuccessActivity(Event.BuyGoodsEvent buyGoodsEvent) {
        Intent intent = new Intent(mContext, BuySuccessActivity.class);
        intent.putExtra("id", goodsId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
