package com.chencha.utlisdemo.hencode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PracticeDrawCircleView extends View {
    public PracticeDrawCircleView(Context context) {
        super(context);
    }

    public PracticeDrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //使用  canvas.drawCircle(); 制作 1.实心圆，2.空心圆 3.修改颜色实心圆 4. 边框宽度
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(350, 150, 150, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(750, 150, 150, mPaint);


        mPaint.setColor(Color.parseColor("#333333"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(350, 500, 150, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(700, 500, 150, mPaint);


    }
}
