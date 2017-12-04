package com.yitahutu.cn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.yitahutu.cn.R;
import com.yitahutu.cn.ui.fragment.MallDetailBaseFragment;

/**
 * Created by Administrator on 2017\11\7 0007.
 */
public class BuySuccessActivity extends BaseActivity {
    @Override
    void setRightIconListener() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("购买成功", R.layout.activity_buy_success);
    }
    public void look(View view){
        Intent intent = new Intent(mContext,MallDetailBaseActivity.class);
        intent.putExtra("page",2);
        startActivity(intent);
    }

    public void goMall(View view){
        Intent intent = new Intent(mContext,MainActivity.class);
        intent.putExtra("isBalance",true);
        startActivity(intent);
    }

}
