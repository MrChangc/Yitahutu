package com.yitahutu.cn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.ui.activity.FinancingPastureActivity;
import com.yitahutu.cn.ui.activity.MainActivity;
import com.yitahutu.cn.ui.activity.MemberActivity;
import com.yitahutu.cn.ui.activity.RechargeActivity;
import com.yitahutu.cn.ui.activity.RegisterActivity;
import com.yitahutu.cn.ui.activity.TestActivity;
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
    public void setButtonLogin() {
        Intent intent = new Intent(getActivity(), UserLoginActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.button_register)
    public void setButtonRegister() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.button_member)
    public void setButtonMember() {
        Intent intent = new Intent(getActivity(), MemberActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick({R.id.ll_shift_to, R.id.button_subscription, R.id.ll_ramble, R.id.button_shop, R.id.ll_direct, R.id.button_direct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shift_to:
                gotoActivity(RechargeActivity.class);
                break;
            case R.id.button_subscription:
                gotoActivity(FinancingPastureActivity.class);
                break;
            case R.id.ll_ramble:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("isBalance",true);
                getActivity().startActivity(intent);
                break;
            case R.id.button_shop:
                gotoActivity(MemberActivity.class);
                break;
            case R.id.ll_direct:
                break;
            case R.id.button_direct:
                Log.e("button_direct","ok");
                break;
        }
    }
    private void gotoActivity(Class aClass){
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), aClass);
            getActivity().startActivity(intent);
        }else
            Toast.makeText(getActivity(),"请先登录!",Toast.LENGTH_SHORT);
    }

}
