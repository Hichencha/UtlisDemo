package com.chencha.utlisdemo.vr;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.chencha.utlisdemo.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class VrActivity extends AppCompatActivity {
    public VrPanoramaView vrPanoramaView;
    private TextView tv_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_layout);
        tv_title = findViewById(R.id.tv_title);
        vrPanoramaView = findViewById(R.id.vrPanoramaView);
        vrPanoramaView.setTouchTrackingEnabled(true);
        vrPanoramaView.setFullscreenButtonEnabled(false);
        vrPanoramaView.setInfoButtonEnabled(false);
        vrPanoramaView.setStereoModeButtonEnabled(false);

        VrPanoramaView.Options options = new VrPanoramaView.Options();
        //设置图片类型为单通道图片
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
        vrPanoramaView.loadImageFromBitmap(getImageFromAssetsFile(this, "aa.JPG"), options);

    }


    private static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager manager = context.getResources().getAssets();
        try {
            InputStream inputStream = manager.open(fileName);
            image = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    @Override
    protected void onResume() {
        super.onResume();
        vrPanoramaView.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vrPanoramaView.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        super.onDestroy();
    }
}
