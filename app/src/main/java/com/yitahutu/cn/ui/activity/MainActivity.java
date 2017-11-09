package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.ui.fragment.MainFragment;
import com.yitahutu.cn.ui.fragment.MallFragment;
import com.yitahutu.cn.ui.fragment.PastureFragment;
import com.yitahutu.cn.ui.fragment.UserInfoFragment;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private MainFragment fragmentMain;
    private PastureFragment fragmentPasture;
    private MallFragment fragmentMall;
    private UserInfoFragment fragmentUser;
    @BindView(R.id.contact_radio1)
    RadioButton contactRadio1;
    @BindView(R.id.contact_radio2)
    RadioButton contactRadio2;
    @BindView(R.id.contact_radio3)
    RadioButton contactRadio3;
    @BindView(R.id.contact_radio4)
    RadioButton contactRadio4;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isBalance = getIntent().getBooleanExtra("isBalance", false);
        setContentView("首页", R.layout.activity_main);
        ButterKnife.bind(this);
        setMiddleText("首页");
        setLeftButtontVisibility(true);
        setRightIconVisibility(true);
        if (isBalance)
            setContactRadio3();
        else
            initFragment();
    }

    private void initFragment() {
        fragmentMain = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        fragmentPasture = (PastureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_pasture);
        fragmentMall = (MallFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_mall);
        fragmentUser = (UserInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_user);
        getSupportFragmentManager().beginTransaction().show(fragmentMain).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentPasture).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentMall).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentUser).commit();
        contactRadio1.setChecked(true);
        contactRadio2.setChecked(false);
        contactRadio3.setChecked(false);
        contactRadio4.setChecked(false);
    }


    @Override
    void setRightIconListener() {
        if (contactRadio1.isChecked()) {

        } else if (contactRadio2.isChecked()) {
//            WebService.getGoodsList(mContext);
        } else if (contactRadio3.isChecked()) {
            EventBus.getDefault().post(new Event.LoginEvent());
        } else if (contactRadio4.isChecked())
            WebService.getUserInfo(mContext);

    }

    @OnClick(R.id.contact_radio1)
    public void setContactRadio1() {
        getSupportFragmentManager().beginTransaction().show(fragmentMain).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentPasture).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentMall).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentUser).commit();
        contactRadio1.setChecked(true);
        contactRadio2.setChecked(false);
        contactRadio3.setChecked(false);
        contactRadio4.setChecked(false);
        setRightIconVisibility(false);
    }

    @OnClick(R.id.contact_radio2)
    public void setContactRadio2() {
        getSupportFragmentManager().beginTransaction().hide(fragmentMain).commit();
        getSupportFragmentManager().beginTransaction().show(fragmentPasture).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentMall).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentUser).commit();
        contactRadio1.setChecked(false);
        contactRadio2.setChecked(true);
        contactRadio3.setChecked(false);
        contactRadio4.setChecked(false);
        setMiddleText("牧场");
        setRightIconVisibility(true);
    }

    @OnClick(R.id.contact_radio3)
    public void setContactRadio3() {
        getSupportFragmentManager().beginTransaction().hide(fragmentMain).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentPasture).commit();
        getSupportFragmentManager().beginTransaction().show(fragmentMall).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentUser).commit();
        contactRadio1.setChecked(false);
        contactRadio2.setChecked(false);
        contactRadio3.setChecked(true);
        contactRadio4.setChecked(false);
        setMiddleText("商场");
        setRightIconVisibility(true);
    }

    @OnClick(R.id.contact_radio4)
    public void setContactRadio4() {
        getSupportFragmentManager().beginTransaction().hide(fragmentMain).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentPasture).commit();
        getSupportFragmentManager().beginTransaction().hide(fragmentMall).commit();
        getSupportFragmentManager().beginTransaction().show(fragmentUser).commit();
        contactRadio1.setChecked(false);
        contactRadio2.setChecked(false);
        contactRadio3.setChecked(false);
        contactRadio4.setChecked(true);
        setMiddleText("我的");
        setRightIconVisibility(true);

    }
}
