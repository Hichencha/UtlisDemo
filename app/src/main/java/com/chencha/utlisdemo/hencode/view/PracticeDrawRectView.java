package com.chencha.utlisdemo.hencode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PracticeDrawRectView extends View {
    public PracticeDrawRectView(Context context) {
        super(context);
    }

    public PracticeDrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        //矩形   canvas.drawRect();
        // canvas.drawRect(350, 200, 750, 600, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100, 100, 500, 500, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(700, 100, 1100, 500, mPaint);
    }
}
