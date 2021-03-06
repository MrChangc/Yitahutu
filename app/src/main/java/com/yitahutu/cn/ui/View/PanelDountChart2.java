package com.yitahutu.cn.ui.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
public class PanelDountChart2 extends View {
    private int ScrWidth, ScrHeight;

    //演示用的百分比例,实际使用中，即为外部传入的比例参数
    private float[] arrPer = new float[]{20f, 30f, 10f, 40f};
    private final int arrColorRgb[][] = {{255, 255, 0},//#FFFF00
            {65, 105, 225},//#4169E1
            {255, 0, 0},//#FF0000
            {124, 252, 0}};//#7CFC00
    public PanelDountChart2(Context context, float[] ints) {
        super(context);
        // TODO Auto-generated constructor stub
        arrPer = ints;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        ScrHeight = heightMeasureSpec;
//        ScrWidth = widthMeasureSpec;
//        ViewGroup mViewGroup = (ViewGroup) getParent();
//        if(null != mViewGroup){
//            int mParentWidth = mViewGroup.getWidth();
//            int mParentHeight = mViewGroup.getHeight();
//        }
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ScrHeight = w;
        ScrWidth = h;
    }

    public void onDraw(Canvas canvas) {
        //画布背景
        canvas.drawColor(Color.WHITE);

        float cirX = ScrWidth / 2;
        float cirY = ScrHeight / 2;
        float radius = ScrHeight / 2;

        float arcLeft = cirX - radius;
        float arcTop = cirY - radius;
        float arcRight = cirX + radius;
        float arcBottom = cirY + radius;
        RectF arcRF0 = new RectF(0, 0, ScrWidth, ScrWidth);

        //画笔初始化
        Paint PaintArc = new Paint();
        PaintArc.setAntiAlias(true);
        //位置计算类

        float Percentage = 0.0f;
        float CurrPer = 0.0f;
        int i = 0;
        for (i = 0; i < arrPer.length; i++) {
            //将百分比转换为饼图显示角度
            Percentage = 360 * (arrPer[i] / 100);
            Percentage = (float) (Math.round(Percentage * 100)) / 100;
            //分配颜色
            PaintArc.setARGB(255, arrColorRgb[i][0], arrColorRgb[i][1], arrColorRgb[i][2]);
            //在饼图中显示所占比例
            canvas.drawArc(arcRF0, CurrPer, Percentage, true, PaintArc);
            //下次的起始角度
            CurrPer += Percentage;
        }

        //画圆心
        PaintArc.setColor(Color.WHITE);
        canvas.drawCircle(cirX, cirY, radius / 2, PaintArc);

    }

}
