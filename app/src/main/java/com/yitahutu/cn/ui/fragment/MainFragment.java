package com.yitahutu.cn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.activity.MemberActivity;
import com.yitahutu.cn.ui.activity.RegisterActivity;
import com.yitahutu.cn.ui.activity.UserLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\11 0011.
 */
public class MainFragment extends Fragment {
    @BindView(R.id.button_login)
    LinearLayout buttonLogin;
    @BindView(R.id.button_register)
    LinearLayout buttonRegister;
    @BindView(R.id.button_member)
    LinearLayout buttonMember;
    @BindView(R.id.ll_shift_to)
    LinearLayout llShiftTo;
    @BindView(R.id.button_subscription)
    Button buttonSubscription;
    @BindView(R.id.ll_ramble)
    LinearLayout llRamble;
    @BindView(R.id.button_shop)
    Button buttonShop;
    @BindView(R.id.ll_direct)
    LinearLayout llDirect;
    @BindView(R.id.button_direct)
    Button buttonDirect;
    Unbinder unbinder;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.button_login)
    public void setButtonLogin(){
        Intent intent= new Intent(getActivity(), UserLoginActivity.class);
        getActivity().startActivity(intent);
    }
    @OnClick(R.id.button_register)
    public void setButtonRegister(){
        Intent intent= new Intent(getActivity(), RegisterActivity.class);
        getActivity().startActivity(intent);
    }
    @OnClick(R.id.button_member)
    public void setButtonMember(){
        Intent intent= new Intent(getActivity(), MemberActivity.class);
        getActivity().startActivity(intent);
    }
}
