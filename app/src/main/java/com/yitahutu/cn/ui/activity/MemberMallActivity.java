package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.adapter.MemberMallAdapter;
import com.yitahutu.cn.webservice.SuccessCallBack;
import com.yitahutu.cn.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\1\4 0004.
 */
public class MemberMallActivity extends BaseActivity implements SuccessCallBack, AdapterView.OnItemClickListener {
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_member_name)
    TextView tvMemberName;
    @BindView(R.id.tv_member_data)
    TextView tvMemberData;
    @BindView(R.id.gv_member)
    GridView gvMember;
    private List<GoodsModel> goodsModels ;
    private MemberMallAdapter mallAdapter;
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("会员商城", R.layout.activity_member_all);
        ButterKnife.bind(this);
        goodsModels = new ArrayList<>();
        initData();
        mallAdapter = new MemberMallAdapter(goodsModels,mContext);
        gvMember.setAdapter(mallAdapter);
        gvMember.setOnItemClickListener(this);
        WebService.getGoodsList(mContext,"5",this);
    }
    private void initData() {
        UserInfoModel userInfoModel = MyApplication.getUserInfoModel();
        int vip = userInfoModel.getVip();

        if (vip == 0) {
            tvMemberName.setText(userInfoModel.getName());
            tvMemberData.setText("购买成为会员");
            ivImage.setImageResource(R.mipmap.member_vip_no);
        } else {
            tvMemberName.setText(userInfoModel.getName() + "会员您好");
            String date = TimeUtils.getTime7(userInfoModel.getVipExpireTime());
            tvMemberData.setText(date + "到期");
            ivImage.setImageResource(R.mipmap.member_vip);
        }
        Picasso.with(this).load(userInfoModel.getUrl()).error(getResources().getDrawable(R.mipmap.member_head)).into(imageHead);
    }

    @Override
    public void callBack() {


    }

    @Override
    public void callBackToObject(Object o) {
        List<GoodsModel> models = (List<GoodsModel>) o;
        goodsModels.addAll(models);
        mallAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GoodsModel goodsModel = goodsModels.get(i);
        if (goodsModel != null) {
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            intent.putExtra("goods_id", goodsModel.getId());
            startActivity(intent);
        }
    }
}
