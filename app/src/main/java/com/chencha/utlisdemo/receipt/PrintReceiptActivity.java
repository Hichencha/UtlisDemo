package com.chencha.utlisdemo.receipt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.chencha.utlisdemo.R;

public class PrintReceiptActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_info;
    private Animation animator;
    private Button btn_ticket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outticket_layout);
        rl_info = (RelativeLayout) findViewById(R.id.rl_info);
        btn_ticket = (Button) findViewById(R.id.btn_ticket);
        btn_ticket.setOnClickListener(v -> {
            startAnimation();
        });
    }

    private void startAnimation() {
        animator = AnimationUtils.loadAnimation(this, R.anim.slide_down_in);
        animator.setDuration(1000);
        animator.setFillAfter(true);
        rl_info.startAnimation(animator);
    }

    @Override
    public void onClick(View v) {

    }
}
