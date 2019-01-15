package com.chencha.utlisdemo.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.chencha.utlisdemo.R;

public class CountTimeActivity extends AppCompatActivity implements View.OnClickListener, TimeView.DownCountTimerListener {

    Button mBtn;
    private TimeView mTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_view_activity);
        mBtn = (Button) findViewById(R.id.button1);
        mBtn.setOnClickListener(this);
        mTimeView = (TimeView) findViewById(R.id.clockView);
        mTimeView.setDownCountTimerListener(this);

        mTimeView.setDownCountTime(100000L * 60L + 1000L * 12L);
        mTimeView.startDownCountTimer();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
//                mTimeView.setDownCountTime(100000L * 60L + 1000L * 12L);
//                mTimeView.startDownCountTimer();
                break;

            default:
                break;
        }
    }

    @Override
    public void stopDownCountTimer() {
        Toast.makeText(this,"结束了",Toast.LENGTH_SHORT).show();
    }
}

