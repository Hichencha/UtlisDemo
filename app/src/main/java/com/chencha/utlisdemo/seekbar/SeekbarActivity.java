package com.chencha.utlisdemo.seekbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import com.chencha.utlisdemo.R;

public class SeekbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar_act_layout);

        //文本
        final TextView textView = findViewById(R.id.seek_text);

        //seekbar
        SeekBar seekBar = findViewById(R.id.seekbar);
//        //禁止滑动
//        seekBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//        textView.setText(String.valueOf(50) + "%");


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //设置文本显示
                textView.setText(String.valueOf(progress) + "%");
                //获取文本宽度
                float textWidth = textView.getWidth();

                //获取seekbar最左端的x位置
                float left = seekBar.getLeft();

                //进度条的刻度值
                float max = Math.abs(seekBar.getMax());

                //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
                float thumb = dip2px(SeekbarActivity.this, 15);

                //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                float average = (((float) seekBar.getWidth()) - 2 * thumb) / max;

                //int to float
                float currentProgress = progress;

                //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                float pox = left - textWidth / 2 + thumb + average * currentProgress;
                textView.setX(pox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
