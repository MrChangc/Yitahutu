package com.yitahutu.cn.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\11 0011.
 */
public abstract class BaseActivity extends FragmentActivity {

    ImageView leftButton;
    TextView middleText;
    TextView rightText;
    ImageView rightIcon;
    RelativeLayout titleLayout;
    LinearLayout bodyLayout;
    private LayoutInflater inflater;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        initView();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRightIcon();
            }
        });
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRightIcon();
            }
        });
        mContext = this;
    }

    private void initView() {
        leftButton = (ImageView) findViewById(R.id.left_button);
        rightIcon = (ImageView) findViewById(R.id.right_icon);
        middleText = (TextView) findViewById(R.id.middle_text);
        rightText = (TextView) findViewById(R.id.right_text);
        bodyLayout = (LinearLayout) findViewById(R.id.body_layout);
    }

    ;

    public void setLeftButton() {
        finish();
    }

    public void setRightIcon() {
        setRightIconListener();
    }

    abstract void setRightIconListener();

    public void setLeftButtontVisibility(boolean istVisibility) {
        if (istVisibility)
            leftButton.setVisibility(View.VISIBLE);
        else
            leftButton.setVisibility(View.GONE);
    }

    public void setRightIconVisibility(boolean istVisibility) {
        if (istVisibility)
            rightIcon.setVisibility(View.VISIBLE);
        else
            rightIcon.setVisibility(View.GONE);
    }

    public void setRightTextVisibility(boolean istVisibility) {
        if (istVisibility)
            rightText.setVisibility(View.VISIBLE);
        else
            rightText.setVisibility(View.GONE);
    }

    public void setRightText(String text) {
        rightText.setText(text);
    }

    public void setMiddleTextVisibility(boolean istVisibility) {
        if (istVisibility)
            middleText.setVisibility(View.VISIBLE);
        else
            middleText.setVisibility(View.GONE);
    }

    public void setMiddleText(String text) {
        middleText.setText(text);
    }

    protected void setContentView(String text, int id) {
        setMiddleText(text);
        inflater.inflate(id, bodyLayout);
    }


}
