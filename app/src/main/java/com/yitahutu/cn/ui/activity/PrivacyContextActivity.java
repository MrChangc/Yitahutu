package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\11\24 0024.
 */
public class PrivacyContextActivity extends BaseActivity {
    @BindView(R.id.webView1)
    WebView mWebView;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("隐私协议", R.layout.activity_privacy_context);
        ButterKnife.bind(this);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        mWebView.loadUrl("file:///android_asset/privacy_context.html");
    }
}
