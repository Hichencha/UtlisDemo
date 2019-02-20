package com.chencha.utlisdemo;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context mContext;
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
    }
}
