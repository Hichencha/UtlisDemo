package com.chencha.utlisdemo.tagview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.chencha.utlisdemo.R;

public class TagViewActivity extends AppCompatActivity {
    TagGoodsDetailsTextView mTagTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_view_layout);
        mTagTv = (TagGoodsDetailsTextView) findViewById(R.id.tag_tv);

        mTagTv.setContentTag("你好", "2019加油", "自营");


    }
}

