package com.chencha.utlisdemo.hencode.view;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.chencha.utlisdemo.R;

/**
 * view 绘制基础
 */
public class HenCodeView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //1. LinearGradient 线条渐变 TileMode 有三中模式 CLAMP  MIRROR  REPEAT
    //2. RadialGradient 辐射渐变
    //3. SweepGradient 扫描渐变
    //4. BitemShader
    // 5. setColirFilter
    Bitmap bitmap;
    ColorFilter colorFilter1 = new LightingColorFilter(0x00ffff, 0x000000);
    ColorFilter colorFilter2 = new LightingColorFilter(0xffffff, 0x003000);

    public HenCodeView(Context context) {
        super(context);
    }

    public HenCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HenCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
//        paint.setShader(new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT));

//        paint.setShader(new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP));

//        paint.setShader(new SweepGradient(300, 300, Color.parseColor("#E91E63"), Color.parseColor("#2196F3")));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(200, 200, 100, paint);

        paint.setColorFilter(colorFilter1);
        canvas.drawBitmap(bitmap, 0, 0, paint);

//        paint.setColorFilter(colorFilter2);
//        canvas.drawBitmap(bitmap, 50, 50, paint);

    }


}
