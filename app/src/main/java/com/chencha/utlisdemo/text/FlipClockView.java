package com.chencha.utlisdemo.text;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 日历3D翻转效果
 */
public class FlipClockView extends FrameLayout {

    private TextView mVisibleTextView;// 可见的
    private TextView mInvisibleTextView;// 不可见

    private int layoutWidth;
    private int layoutHeight;
    private Scroller mScroller;

    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();
    private Rect mTopRect = new Rect();
    private Rect mBottomRect = new Rect();
    private boolean isUp2Down = true;
    private Paint mShinePaint = new Paint();
    private Paint mShadePaint = new Paint();
    private boolean isFlipping = false;

    public FlipClockView(Context context) {
        this(context, null);
    }

    public FlipClockView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public FlipClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());// 减速 // 动画插入器

        mInvisibleTextView = new TimeTextView(context);
        mInvisibleTextView.setText("0");
        mInvisibleTextView.setGravity(Gravity.CENTER);
        mInvisibleTextView.setIncludeFontPadding(false);
        addView(mInvisibleTextView);

        mVisibleTextView = new TimeTextView(context);
        mVisibleTextView.setText("0");
        mVisibleTextView.setGravity(Gravity.CENTER);
        mVisibleTextView.setIncludeFontPadding(false);
        addView(mVisibleTextView);

        mShadePaint.setColor(Color.BLACK);
        mShadePaint.setStyle(Paint.Style.FILL);
        mShinePaint.setColor(Color.WHITE);
        mShinePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        layoutHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        //将两个textView放置进去
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.layout(0, 0, layoutWidth, layoutHeight);
        }

        mTopRect.top = 0;
        mTopRect.left = 0;
        mTopRect.right = getWidth();
        mTopRect.bottom = getHeight() / 2;

        mBottomRect.top = getHeight() / 2;
        mBottomRect.left = 0;
        mBottomRect.right = getWidth();
        mBottomRect.bottom = getHeight();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
            drawTopHalf(canvas);
            drawBottomHalf(canvas);
            drawFlipHalf(canvas);
            postInvalidate();
        } else {
            if (isFlipping) {
                showViews(canvas);
            }

            if (mScroller.isFinished() && !mScroller.computeScrollOffset()) {
                isFlipping = false;
            }
        }
    }

    /**
     * 显示需要显示的数字
     *
     * @param canvas
     */
    private void showViews(Canvas canvas) {
        String current = mVisibleTextView.getText().toString();
        String past = mInvisibleTextView.getText().toString();
        // Log.e("需要显示的数字--->",current+ "%%    "+past);

        mVisibleTextView.setText(past);
        mInvisibleTextView.setText(current);

        // 防止切换抖动
        drawChild(canvas, mVisibleTextView, 0);
    }

    /** 画下半部分 */
    private void drawBottomHalf(Canvas canvas) {
        canvas.save();
        canvas.clipRect(mBottomRect);
        View drawView = !isUp2Down ? mInvisibleTextView : mVisibleTextView;
        drawChild(canvas, drawView, 0);
        canvas.restore();
    }

    /** 画上半部分 */
    private void drawTopHalf(Canvas canvas) {
        canvas.save();

        canvas.clipRect(mTopRect);
        View drawView = !isUp2Down ? mVisibleTextView : mInvisibleTextView;
        drawChild(canvas, drawView, 0);

        canvas.restore();

    }

    /** 画翻页部分 */
    private void drawFlipHalf(Canvas canvas) {
        canvas.save();
        mCamera.save();

        View view = null;
        float deg = getDeg();
        if (deg > 90) {
            canvas.clipRect(!isUp2Down ? mTopRect : mBottomRect);
            mCamera.rotateX(!isUp2Down ? deg - 180 : -(deg - 180));
            view = mInvisibleTextView;
        } else {
            canvas.clipRect(!isUp2Down ? mBottomRect : mTopRect);
            mCamera.rotateX(!isUp2Down ? deg : -deg);
            view = mVisibleTextView;
        }

        mCamera.getMatrix(mMatrix);
        positionMatrix();
        canvas.concat(mMatrix);

        if (view != null) {
            drawChild(canvas, view, 0);
        }

        drawFlippingShadeShine(canvas);

        mCamera.restore();
        canvas.restore();
    }

    private float getDeg() {
        return mScroller.getCurrY() * 1.0f / layoutHeight * 180;
    }

    /** 绘制翻页时的阳面和阴面 */
    private void drawFlippingShadeShine(Canvas canvas) {
        final float degreesFlipped = getDeg();
        // Log.d(TAG, "deg: " + degreesFlipped);
        if (degreesFlipped < 90) {
            final int alpha = getAlpha(degreesFlipped);
            // Log.d(TAG, "小于90度时的透明度-------------------> " + alpha);
            mShinePaint.setAlpha(alpha);
            mShadePaint.setAlpha(alpha);
            canvas.drawRect(!isUp2Down ? mBottomRect : mTopRect, !isUp2Down ? mShinePaint
                    : mShadePaint);
        } else {
            final int alpha = getAlpha(Math.abs(degreesFlipped - 180));
            // Log.d(TAG, "大于90度时的透明度-------------> " + alpha);
            mShadePaint.setAlpha(alpha);
            mShinePaint.setAlpha(alpha);
            canvas.drawRect(!isUp2Down ? mTopRect : mBottomRect, !isUp2Down ? mShadePaint
                    : mShinePaint);
        }
    }

    private int getAlpha(float degreesFlipped) {
        return (int) ((degreesFlipped / 90f) * 100);
    }

    private void positionMatrix() {
        mMatrix.preScale(0.25f, 0.25f);
        mMatrix.postScale(4.0f, 4.0f);
        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
    }

    /** 初始化隐藏textView显示的值 */
    private void initTextView() {
        int visibleValue = Integer.parseInt(mVisibleTextView.getText()
                .toString());
        //	int invisibleValue = isUp2Down ? visibleValue - 1 : visibleValue + 1;//这里控制是 + 还是 -
        int invisibleValue = visibleValue - 1;
        if (invisibleValue < 10) {
            mInvisibleTextView.setText("0" + invisibleValue);
        } else {
            mInvisibleTextView.setText("" + invisibleValue);
        }
    }

    /**
     *
     * @param isUp2Down
     *     方向标识 true: 从上往下翻  , false: 从下往上翻
     */
    public void setFlipDirection(boolean isUp2Down) {
        this.isUp2Down = isUp2Down;
    }

    public void smoothFlip() {
        //Log.e(TAG, "翻动 ");
        initTextView();
        isFlipping = true;
        mScroller.startScroll(0, 0, 0, layoutHeight, 700);
        postInvalidate();
    }

    public TextView getmVisibleTextView() {
        return mVisibleTextView;
    }

    public TextView getmInvisibleTextView() {
        return mInvisibleTextView;
    }

    public boolean isFlipping() {
        return isFlipping && !mScroller.isFinished()
                && mScroller.computeScrollOffset();
    }

    /**
     * 获取当前View值
     *
     * @return
     */
    public int getCurrentValue() {
        return Integer.parseInt(mVisibleTextView.getText().toString());
    }

    /**
     * 设置view的时间值
     * @param textTime
     */
    public void setClockTime(String textTime) {
        mVisibleTextView.setText(textTime);
    }

    /**
     * 设置时间数字的背景
     * @param drawable
     */
    public void setClockBackground(Drawable drawable) {
        mVisibleTextView.setBackground(drawable);
        mInvisibleTextView.setBackground(drawable);
    }

    /**
     * 设置时间数字的颜色
     * @param color
     */
    public void setClockTextColor(int color) {
        mVisibleTextView.setTextColor(color);
        mInvisibleTextView.setTextColor(color);
    }

    /**
     * 设置时间数字的大小
     * @param size
     */
    public void setClockTextSize(float size){
        mVisibleTextView.setTextSize(size);
        mInvisibleTextView.setTextSize(size);
    }

}
