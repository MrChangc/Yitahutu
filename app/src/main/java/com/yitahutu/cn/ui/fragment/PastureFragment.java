package com.yitahutu.cn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.activity.FinancingPastureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\13 0013.
 */
public class PastureFragment extends Fragment {
    @BindView(R.id.rel_cowboy)
    RelativeLayout relCowboy;
    @BindView(R.id.rel_financing)
    RelativeLayout relFinancing;
    Unbinder unbinder;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pasture, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rel_cowboy, R.id.rel_financing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_cowboy:
                Intent intent = new Intent(getActivity(), FinancingPastureActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rel_financing:
                break;
        }
    }
}
