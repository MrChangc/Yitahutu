package com.yitahutu.cn.ui.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.yitahutu.cn.R;

/**
 * Created by Administrator on 2017\10\18 0018.
 */
public class MyChatView extends View {

    //圆的粗细
    private float mStrokeWidth = 5;
    //圆环的画笔
    private Paint cyclePaint;
    //文字的画笔
    private Paint textPaint;

    //文字颜色
    private int textColor = 0xFF000000;
    //文字大小
    private int textSize = 30;

    //View自身的宽和高
    private int mHeight;
    private int mWidth;



    private double rate;

    public MyChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyChatView(Context context,double rate) {
        super(context);
        this.rate = rate;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        initPaint();
        drawCycle(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        textPaint.setColor(getResources().getColor(R.color.black));
        canvas.drawText("已 售 "+rate*100+" %", mWidth/5, mHeight/4+20, textPaint);
        textPaint.setColor(getResources().getColor(R.color.title_back_ground));
        canvas.drawText("立 即 购 买", mWidth/5, mHeight/4*3-20, textPaint);
    }

    private void initPaint() {
        //边框画笔
        cyclePaint = new Paint();
        cyclePaint.setAntiAlias(true);
        cyclePaint.setStyle(Paint.Style.STROKE);
        cyclePaint.setStrokeWidth(mStrokeWidth);
        //文字画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(textSize);
    }

    /**
     * 画圆环
     *
     * @param canvas
     */
    private void drawCycle(Canvas canvas) {
        float startPercent = 0;
        float sweepPercent = (float) (360 * rate);
        cyclePaint.setColor(getResources().getColor(R.color.title_back_ground));
        canvas.drawArc(new RectF(10, 0, mHeight-10, mHeight-10), startPercent, sweepPercent, false, cyclePaint);
        cyclePaint.setColor(getResources().getColor(R.color.color_background));
        canvas.drawArc(new RectF(10, 0, mHeight-10, mHeight-10), sweepPercent, 360-sweepPercent, false, cyclePaint);

    }


}
