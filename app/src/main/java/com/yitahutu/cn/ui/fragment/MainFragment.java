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
import android.widget.Toast;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.ui.activity.FinancingPastureActivity;
import com.yitahutu.cn.ui.activity.HappyPatureActivity;
import com.yitahutu.cn.ui.activity.MainActivity;
import com.yitahutu.cn.ui.activity.MemberMallActivity;
import com.yitahutu.cn.ui.activity.SignActivity;
import com.yitahutu.cn.ui.activity.TestActivity;

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
    @BindView(R.id.iv_member)
    ImageView ivMember;
    @BindView(R.id.iv_shengtai)
    ImageView ivShengtai;
    @BindView(R.id.iv_yingyang)
    ImageView ivYingyang;
    @BindView(R.id.iv_finance)
    ImageView ivFinance;
    @BindView(R.id.iv_zhibo)
    ImageView ivZhibo;
    Unbinder unbinder;
    @BindView(R.id.ll_sign)
    LinearLayout llSign;
    @BindView(R.id.ll_pasture)
    LinearLayout llPasture;
    @BindView(R.id.ll_happy)
    LinearLayout llHappy;
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

    @OnClick({R.id.iv_member, R.id.iv_shengtai, R.id.iv_yingyang, R.id.iv_finance, R.id.iv_zhibo,R.id.ll_sign, R.id.ll_pasture, R.id.ll_happy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_member:
                gotoActivity(MemberMallActivity.class);
                break;
            case R.id.iv_shengtai:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoMall();
                break;
            case R.id.iv_yingyang:
                break;
            case R.id.iv_finance:
                gotoActivity(FinancingPastureActivity.class);
                break;
            case R.id.iv_zhibo:
                gotoActivity(TestActivity.class);
                break;
            case R.id.ll_sign:
                gotoActivity(SignActivity.class);
                break;
            case R.id.ll_pasture:
                gotoActivity(HappyPatureActivity.class);
                break;
            case R.id.ll_happy:
                break;
        }
    }

    private void gotoActivity(Class aClass) {
        if (PreferUtil.isLogin()) {
            Intent intent = new Intent(getActivity(), aClass);
            getActivity().startActivity(intent);
        } else
            Toast.makeText(getActivity(), "请先登录!", Toast.LENGTH_SHORT);
    }
}
