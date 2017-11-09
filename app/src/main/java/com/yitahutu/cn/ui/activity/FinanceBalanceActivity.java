package com.yitahutu.cn.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.View.PaymentDialog;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class FinanceBalanceActivity extends BaseActivity {
    @BindView(R.id.text_user_name)
    TextView textUserName;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.text_phone_number)
    TextView textPhoneNumber;
    @BindView(R.id.ll_address_info)
    LinearLayout llAddressInfo;
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
    @BindView(R.id.text_total)
    TextView textTotal;
    @BindView(R.id.payment)
    LinearLayout payment;
    private long id = -1;
    private String goodsId = "-1";
    private String addressId = "-1"  ;
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView("购买商品", R.layout.activity_finance_balance);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        Bundle bundle = getIntent().getExtras();
        FinanceModel financeModel = (FinanceModel) bundle.getSerializable("finance");
        if (financeModel!=null){
            textGoodsName.setText(financeModel.getHeadline());
            textGoodsPrice.setText("￥ " + financeModel.getPrice());
            textCount.setText("1");
            goodsId = financeModel.getId()+"";
            Picasso.with(mContext).load(ConstantUtils.baseUrl+"/resource/raceType/49491997.jpg").into(imageGoods);
        }
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
                addressId = addressModel.getId()+"";
                return;
            }
        }
        if (!isCheck){
            AddressModel addressModel = models.get(0);
            textAddress.setText(addressModel.getDetailAddress());
            textUserName.setText(addressModel.getName());
            textPhoneNumber.setText(addressModel.getPhone());
            addressId = addressModel.getId()+"";
        }

    }


    @OnClick({R.id.ll_address_info, R.id.image_add, R.id.image_minus, R.id.payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address_info:
                gotoAddressListActivity();
                break;
            case R.id.image_add:
                int addCount = Integer.valueOf(textCount.getText().toString());
                textCount.setText((addCount + 1) + "");
                break;
            case R.id.image_minus:
                int count = Integer.valueOf(textCount.getText().toString());
                if (count <= 1)
                    Toast.makeText(mContext, "个数不能为零", Toast.LENGTH_SHORT).show();
                else
                    textCount.setText((count - 1) + "");
                break;
            case R.id.payment:
                showPaymentDialog();
                break;
        }
    }

    private void gotoAddressListActivity() {
        Intent intent = new Intent(mContext, AddressListActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == 1) {
            id = intent.getLongExtra("id", -1);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showPaymentDialog() {
//        if (MyApplication.getUserInfoModel() != null) {
        PaymentDialog paymentDialog = new PaymentDialog(mContext, new PaymentDialog.BalanceOnClick() {
            @Override
            public void onBalance() {
                WebService.buyGoods(addressId,goodsId,textCount.getText().toString(),"3",mContext);
            }
        });
        paymentDialog.create();
        if (MyApplication.getUserInfoModel() != null) {
            paymentDialog.setTextNumber(MyApplication.getUserInfoModel().getName());
        }
        paymentDialog.setTextCount(Integer.valueOf(textCount.getText().toString()) + "");
        paymentDialog.setTextType("牛币");
        paymentDialog.show();
//        }else
//            Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        setAddress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void gotoBuySuccessActivity(Event.BuyGoodsEvent buyGoodsEvent){
        if (goodsId!="-1"){
            Intent intent = new Intent(mContext,BuySuccessActivity.class);
            intent.putExtra("id",goodsId);
            startActivity(intent);
        }else if (addressId!="-1"){
            Intent intent = new Intent(mContext,BuySuccessActivity.class);
            intent.putExtra("id",goodsId);
        }
    }

}
