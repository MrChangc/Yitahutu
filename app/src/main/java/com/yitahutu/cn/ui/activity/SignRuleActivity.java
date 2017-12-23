package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\12\12 0012.
 */
public class SignRuleActivity extends BaseActivity {
    @BindView(R.id.webView1)
    WebView mWebView;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("签到规则", R.layout.activity_sign_rule);
        ButterKnife.bind(this);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        mWebView.loadUrl("file:///android_asset/sign_rule.html");
    }
}
