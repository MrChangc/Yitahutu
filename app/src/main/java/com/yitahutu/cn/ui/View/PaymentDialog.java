package com.yitahutu.cn.ui.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class PaymentDialog extends Dialog {
    private ImageView imageDismiss;
    private TextView textNumber;
    private TextView textType;
    private TextView textCount;
    private Context mContext;
    private Button button;
    private BalanceOnClick balanceOnClick;
    public PaymentDialog(Context context,BalanceOnClick balanceOnClick) {
        super(context);
        mContext = context;
        this.balanceOnClick = balanceOnClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_payment);
        textNumber = (TextView) findViewById(R.id.text_number);
        textCount = (TextView) findViewById(R.id.text_count);
        textType = (TextView) findViewById(R.id.text_type);
        imageDismiss = (ImageView) findViewById(R.id.image_dismiss);
        imageDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        button = (Button) findViewById(R.id.button_balance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceOnClick.onBalance();
                dismiss();
            }
        });
        Activity activity = (Activity) mContext;
        Window window = getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wl);
        // 设置显示位置
        onWindowAttributesChanged(wl);
        // 设置点击外围解散
        setCanceledOnTouchOutside(false);
    }

    public void setTextNumber(String text){
        textNumber.setText(text);
    }
    public void setTextCount(String text){
        textCount.setText(text);
    }
    public void setTextType(String text){
        textType.setText(text);
    }



    public interface BalanceOnClick{
        void onBalance();
    }
}
