package com.chencha.utlisdemo.tagview;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.chencha.utlisdemo.R;

public class TagGoodsDetailsTextView extends android.support.v7.widget.AppCompatTextView {

    private StringBuffer content_buffer;
    private TextView tv_tag;
    private View view;//标签布局的最外层布局
    private Context mContext;

    //必须重写所有的构造器，否则可能会出现无法inflate布局的错误！
    public TagGoodsDetailsTextView(Context context) {
        super(context);
        mContext = context;
    }


    public TagGoodsDetailsTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mContext = context;

    }


    public TagGoodsDetailsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    public void setContentTag(String str, String str2, String tags) {
        content_buffer = new StringBuffer();
        content_buffer.append(tags);
        if (!TextUtils.isEmpty(str)) {
            content_buffer.append("  " + str);
            content_buffer.append(str2);
        } else {
            content_buffer.append("  " + str2);
        }
        SpannableStringBuilder spannableString = new SpannableStringBuilder(content_buffer);
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_goods_detalis_layout, null);//R.layout.tag是每个标签的布局
        tv_tag = view.findViewById(R.id.tv_tag);
        tv_tag.setText(tags);
        Bitmap bitmap = convertViewToBitmap(view);
        Drawable d = new BitmapDrawable(bitmap);
        d.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());//缺少这句的话，不会报错，但是图片不回显示
        CustomImageSpan span = new CustomImageSpan(d, CustomImageSpan.ALIGN_FONTCENTER);//图片将对齐
        int startIndex;
        int endIndex;
        startIndex = 0;
        endIndex = startIndex + tags.length();
        //判断是否有描述设置字体颜色
        if (!TextUtils.isEmpty(str)) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color9)), 4, str.length() + 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);

    }


    private static Bitmap convertViewToBitmap(View view) {

        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();


        return bitmap;

    }

    /**
     * 自定义imageSpan实现图片与文字的居中对齐
     */
    class CustomImageSpan extends ImageSpan {

        //自定义对齐方式--与文字中间线对齐
        private static final int ALIGN_FONTCENTER = 2;

        public CustomImageSpan(Drawable drawable, int verticalAlignment) {
            super(drawable, verticalAlignment);
        }

        public CustomImageSpan(Context context, int resourceId) {
            super(context, resourceId);
        }

        public CustomImageSpan(Context context, int resourceId, int verticalAlignment) {
            super(context, resourceId, verticalAlignment);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
                         Paint paint) {

            //draw 方法是重写的ImageSpan父类 DynamicDrawableSpan中的方法，在DynamicDrawableSpan类中，虽有getCachedDrawable()，
            // 但是私有的，不能被调用，所以调用ImageSpan中的getrawable()方法，该方法中 会根据传入的drawable ID ，获取该id对应的
            // drawable的流对象，并最终获取drawable对象
            Drawable drawable = getDrawable(); //调用imageSpan中的方法获取drawable对象
            canvas.save();

            //获取画笔的文字绘制时的具体测量数据
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();

            //系统原有方法，默认是Bottom模式)
            int transY = bottom - drawable.getBounds().bottom;
            if (mVerticalAlignment == ALIGN_BASELINE) {
                transY -= fm.descent;
            } else if (mVerticalAlignment == ALIGN_FONTCENTER) {    //此处加入判断， 如果是自定义的居中对齐
                //与文字的中间线对齐（这种方式不论是否设置行间距都能保障文字的中间线和图片的中间线是对齐的）
                // y+ascent得到文字内容的顶部坐标，y+descent得到文字的底部坐标，（顶部坐标+底部坐标）/2=文字内容中间线坐标
                transY = ((y + fm.descent) + (y + fm.ascent)) / 2 - drawable.getBounds().bottom / 2;
            }

            canvas.translate(x, transY);
            drawable.draw(canvas);
            canvas.restore();
        }

        /**
         * 重写getSize方法，只有重写该方法后，才能保证不论是图片大于文字还是文字大于图片，都能实现中间对齐
         */
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            Drawable d = getDrawable();
            Rect rect = d.getBounds();
            if (fm != null) {
                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
                int fontHeight = fmPaint.bottom - fmPaint.top;
                int drHeight = rect.bottom - rect.top;

                int top = drHeight / 2 - fontHeight / 4;
                int bottom = drHeight / 2 + fontHeight / 4;

                fm.ascent = -bottom;
                fm.top = -bottom;
                fm.bottom = top;
                fm.descent = top;
            }
            return rect.right;
        }
    }
}

