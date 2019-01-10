package com.chencha.utlisdemo.vr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.chencha.utlisdemo.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class VrWebViewActivity extends AppCompatActivity {
    WebView tencent_webview;
    String url = "http://ysc.rhrsh.com/webapp/vr/index.html";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_vr_layout);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        tencent_webview = (WebView) findViewById(R.id.web);
        tencent_webview.loadUrl(url);
        WebSettings webSettings = tencent_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tencent_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
    }
}
