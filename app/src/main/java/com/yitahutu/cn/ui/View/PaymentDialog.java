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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitahutu.cn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\20 0020.
 */
public class PaymentDialog extends Dialog {
    private ImageView imageDismiss,imageSelect;
    private TextView textNumber;
    private TextView textType;
    private TextView textCount;
    private TextView pay_type;
    private Context mContext;
    private Button button;
    private BalanceOnClick balanceOnClick;
    private LinearLayout ll_select,ll_confirm,ll_zhifubao,ll_wei_chat,ll_niubi,ll_pay_type;
    private int type = 0;
    public PaymentDialog(Context context,BalanceOnClick balanceOnClick) {
        super(context);
        mContext = context;
        this.balanceOnClick = balanceOnClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_payment);
        getWindow().setGravity(Gravity.BOTTOM);
        textNumber = (TextView) findViewById(R.id.text_number);
        textCount = (TextView) findViewById(R.id.text_count);
        textType = (TextView) findViewById(R.id.text_type);
        pay_type = (TextView) findViewById(R.id.dialog_pay_type);
        ll_confirm = (LinearLayout) findViewById(R.id.ll_confirm_pay);
        ll_select = (LinearLayout) findViewById(R.id.ll_select_pay);
        imageDismiss = (ImageView) findViewById(R.id.image_dismiss);
        ll_wei_chat = (LinearLayout) findViewById(R.id.ll_wei_chat);
        ll_zhifubao = (LinearLayout) findViewById(R.id.ll_zhifubao);
        ll_niubi = (LinearLayout) findViewById(R.id.ll_niubi);
        imageDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        imageSelect = (ImageView) findViewById(R.id.iv_select);
        ll_pay_type = (LinearLayout) findViewById(R.id.ll_pay_type);
        imageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_confirm.setVisibility(View.VISIBLE);
                ll_select.setVisibility(View.GONE);
            }
        });
        ll_niubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPayType(0);
                ll_confirm.setVisibility(View.VISIBLE);
                ll_select.setVisibility(View.GONE);
            }
        });
        ll_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPayType(1);
                ll_confirm.setVisibility(View.VISIBLE);
                ll_select.setVisibility(View.GONE);
            }
        });
        ll_wei_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPayType(2);
                ll_confirm.setVisibility(View.VISIBLE);
                ll_select.setVisibility(View.GONE);
            }
        });
        button = (Button) findViewById(R.id.button_balance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceOnClick.onBalance(type+"");
                dismiss();
            }
        });
        ll_pay_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_confirm.setVisibility(View.GONE);
                ll_select.setVisibility(View.VISIBLE);
            }
        });
        setPayType(0);
        Activity activity = (Activity) mContext;
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width =activity.getWindowManager().getDefaultDisplay().getWidth();
        wl.height = (int) (activity.getWindowManager().getDefaultDisplay().getHeight()*0.5);
        // 以下这两句是为了保证按钮可以水平满屏
//        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wl);
        // 设置显示位置
//        onWindowAttributesChanged(wl);
        // 设置点击外围解散
        setCanceledOnTouchOutside(false);
    }

    private void setPayType(int type) {
        this.type = type;
        if (type == 0){
            textType.setText("牛币");
        }else if(type ==  1){
            textType.setText("支付宝");
        }else {
            textType.setText("微信");
        }
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
        void onBalance(String type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
