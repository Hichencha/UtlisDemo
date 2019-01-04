package com.chencha.utlisdemo.img;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chencha.utlisdemo.R;
import com.chencha.utlisdemo.Utlis;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ImageFlexboxActivity extends AppCompatActivity {
    List<String> img = new ArrayList<String>();
    Bundle bundle;
    FlexboxLayout groupBuyTopLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_layout);
        img = (List<String>) getIntent().getSerializableExtra("IMG");
        groupBuyTopLayout = (FlexboxLayout) findViewById(R.id.group_buy_top);


        for (int i = 0; i < img.size(); i++) {
            final ResizableImageView resizableImageView = new ResizableImageView(this);
            groupBuyTopLayout.addView(resizableImageView);
            Glide.with(this).asBitmap().load(img.get(i)).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    int imageWidth = resource.getWidth();
                    ViewGroup.LayoutParams para = resizableImageView.getLayoutParams();
                    if (para != null) {
                        para.width = Utlis.getScreenWidth(ImageFlexboxActivity.this) * imageWidth / 750;
                        resizableImageView.setLayoutParams(para);
                    }
                    resizableImageView.setImageBitmap(resource);
                    if (para instanceof FlexboxLayoutManager.LayoutParams) {
                        FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) para;
                        flexboxLp.width = Utlis.getScreenWidth(ImageFlexboxActivity.this) * imageWidth / 750;
                    }
                }
            });

        }
    }
}

