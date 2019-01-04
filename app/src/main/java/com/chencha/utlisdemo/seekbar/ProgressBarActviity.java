package com.chencha.utlisdemo.seekbar;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chencha.utlisdemo.R;

public class ProgressBarActviity extends AppCompatActivity {
    RelativeLayout seekLayout;
    ProgressBar seekbar;
    TextView seek_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_act_layout);

        seekbar = (ProgressBar) findViewById(R.id.seekbar);
        seek_text = (TextView) findViewById(R.id.seek_text);
        seekLayout = (RelativeLayout) findViewById(R.id.seekLayout);
        int txt = 80;
        seekbar.setProgress(txt);

        int w = getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) seek_text.getLayoutParams();
        int pro = seekbar.getProgress();
        params.leftMargin = (w - dip2px(ProgressBarActviity.this, 60)) * pro / 100;
        seek_text.setLayoutParams(params);
        seek_text.setText(new StringBuffer().append(seekbar.getProgress()).append("%"));

    }


    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int getLineLength(String str, float textSize) {
        Paint pFont = new Paint();
        pFont.setTextSize(textSize);
        return (int) pFont.measureText(str);
    }


}
