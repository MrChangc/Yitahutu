package com.yitahutu.cn.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.View.ShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\12\22 0022.
 */
public class HongBaoActivity extends BaseActivity {
    @BindView(R.id.ll_shard)
    LinearLayout llShard;

    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("抢红包", R.layout.activity_hongbao);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_shard)
    public void onViewClicked() {
        ShareDialog shareDialog = new ShareDialog(mContext);
        shareDialog.show();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = shareDialog.getWindow().getAttributes();  //获取对话框当前的参数值
//p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth());    //宽度设置为全屏
        shareDialog.getWindow().setAttributes(p);
    }
}
