package com.chencha.utlisdemo.hencode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PracticeDrawPointView extends View {
    public PracticeDrawPointView(Context context) {
        super(context);
    }

    public PracticeDrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPoint() 方法画点
        //Paint.Cap.ROUND 圆角 BUTT 平角 SQUARE 方角
        Paint mPaint = new Paint();

//        mPaint.setStrokeWidth(60);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(50, 50, mPaint);
//
//
//        mPaint.setStrokeWidth(60);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawPoint(50, 150, mPaint);
//
//        mPaint.setStrokeWidth(60);
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(700, 100, mPaint);

        // canvas.drawRoundRect 圆角背景
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(350, 300, 750, 500, 50, 50, mPaint);

    }
}
