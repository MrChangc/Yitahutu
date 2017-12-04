package com.yitahutu.cn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yitahutu.cn.MyApplication;
import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.ConstantUtils;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.UserInfoModel;
import com.yitahutu.cn.ui.activity.AssetActivity;
import com.yitahutu.cn.ui.activity.FinancingActivity;
import com.yitahutu.cn.ui.activity.MemberActivity;
import com.yitahutu.cn.ui.activity.MessageActivity;
import com.yitahutu.cn.ui.activity.RechargeActivity;
import com.yitahutu.cn.ui.activity.SettingActivity;
import com.yitahutu.cn.ui.activity.ShoppingActivity;
import com.yitahutu.cn.ui.activity.UserLoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class UserInfoFragment extends Fragment {
    @BindView(R.id.ll_user_asset)
    LinearLayout llUserAsset;
    @BindView(R.id.ll_user_shopping)
    LinearLayout llShoppingAsset;
    @BindView(R.id.ll_user_financing)
    LinearLayout llUserFinancing;
    @BindView(R.id.ll_user_recharge)
    LinearLayout llUserRecharge;
    @BindView(R.id.ll_user_message)
    LinearLayout llUserMessage;
    @BindView(R.id.ll_user_member)
    LinearLayout llUserMember;
    @BindView(R.id.ll_user_setting)
    LinearLayout llUserSetting;
    @BindView(R.id.user_info_head_image)
    ImageView imageView;
    @BindView(R.id.text_user_name)
    TextView textView;
    Unbinder unbinder;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, rootView);
        setData();
        return rootView;
    }

    private void setData() {
        UserInfoModel userInfoModel = MyApplication.getUserInfoModel();
        if (userInfoModel != null) {
            textView.setText(userInfoModel.getName());
            Picasso.with(getActivity()).load(ConstantUtils.baseUrl+userInfoModel.getUrl()).into(imageView);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ll_user_asset)
    public void setLlUserAsset() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), AssetActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_shopping)
    public void setLlShoppingAsset() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), ShoppingActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_financing)
    public void setLlUserFinancing() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), FinancingActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_recharge)
    public void setLlUserRecharge() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), RechargeActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_message)
    public void setLlUserMessage() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), MessageActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_member)
    public void setLlUserMember() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), MemberActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ll_user_setting)
    public void setLlUserSetting() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.user_info_head_image)
    public void setImageView() {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            getActivity().startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), UserLoginActivity.class);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.UserInfoEvent infoEvent) {
        setData();
    }
}
