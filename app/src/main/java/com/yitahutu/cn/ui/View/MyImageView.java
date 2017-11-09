package com.yitahutu.cn.ui.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yitahutu.cn.R;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017\10\12 0012.
 */
public class MyImageView extends ImageView {
    private Context context;
    private float asset, balance, commission, bonus;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setAsset(float asset, float balance, float commission, float bonus) {
        this.asset = asset;
        this.balance = balance;
        this.commission = commission;
        this.bonus = bonus;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化
        Paint p = new Paint();
        //分别是左上右下的顺序参数，这里是一个200×200的正方形
        RectF r = new RectF(0, 0, 150, 150);
        p.setColor(Color.BLACK);
        p.setTextSize(18f);
        canvas.drawText("水平百分比", 100, 20, p);
        //用来分别给不同等级的扇形做底色
        int[] color = {
                R.color.title_back_ground,
                R.color.balance,
                R.color.commission,
                R.color.share_ut_bonus,
        };
        //访问SharePreferences存储的records
//        if (asset == 0 && commission == 0 && balance == 0 && bonus == 0){
            canvas.drawArc(r, 0, 360, true, p);
//        }else {
//            //设置旋转角度
//            int range = 0;
//            //和初始角度
//            int ori = 0;
//            DecimalFormat df = new DecimalFormat("#");
//            for (int i = 0; i < 4; i++) {
//                p.setColor(getResources().getColor(color[i]));
//                int level = sp.getInt("test" + (i + 1), 0);
//                //当前的初始角度是在旋转的角度加上一个数据的初始角度
//                ori = ori + range;
//                range = (level * 360) / sum;
//                //计算百分比
//                String percent = df.format((range / 360f) * 100);
//                //对应颜色显示比例
//                canvas.drawText("test" + i + "目前所占的百分比" + percent + "%", 180, 270 + 20 * i, p);
//                //绘制扇形，起始角度，旋转角度
//                canvas.drawArc(r, ori, range, true, p);
//            }
//        }
//            //默认值设为1，防止第一次没有sum报错，这是作为分母
//            int sum = sp.getInt("sum", 1);
//        //设置旋转角度
//        int range = 0;
//        //和初始角度
//        int ori = 0;
//        //规范百分比


    }

}