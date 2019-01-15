package com.chencha.utlisdemo.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimeTextView extends TextView {
    Paint mPaint;
    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public TimeTextView(Context context) {
        this(context, null);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, getMeasuredHeight() / 2 - 1, getMeasuredWidth(), getMeasuredHeight() / 2 + 1, mPaint);

    }

}
