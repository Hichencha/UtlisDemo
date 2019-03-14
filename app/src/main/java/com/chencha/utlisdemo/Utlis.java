package com.chencha.utlisdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class Utlis {
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    /**
     * IMEI
     *
     * @return
     */
    @SuppressLint({"MissingPermission"})
    public static String getIMEI() {
        return ((TelephonyManager) App.getInstance().getSystemService(
                Context.TELEPHONY_SERVICE)).getDeviceId();
    }


    /**
     * android id
     *
     * @return
     */
    public static String getAndroidId() {
        return android.provider.Settings.Secure.getString(
                App.getInstance().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

    }

    /**
     *  simserNUmber
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getSimSerialNumber() {
        return ((TelephonyManager) App.getInstance().getSystemService(
                Context.TELEPHONY_SERVICE)).getSimSerialNumber();
    }


    /**
     * getSerialNumber
     *
     * @return result is same to getSerialNumber1()
     */

    public static String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }




}
