package com.chencha.utlisdemo.selectimg;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chencha.utlisdemo.R;

import java.io.*;


public class ImageSelectActivity extends AppCompatActivity {
    public static final String SDCARD_PATH = "/sdcard/TESTIMG/";
    private final int GET_PERMISSION_REQUEST = 100;
    private final int GET_CALL_PERMISSION_REQUEST = 9;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_img_layout);
        img = findViewById(R.id.img);

        img.setOnClickListener(v -> {
            upLoadDialogShow();
        });


    }


    /**
     * 头像
     */
    public void upLoadDialogShow() {
        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.dialog_select_photo);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Window mWindow = dialog.getWindow();
        mWindow.setGravity(Gravity.BOTTOM);
        Button takePotoBtn = mWindow.findViewById(R.id.take_poto_btn);
        Button takeCameraBtn = mWindow
                .findViewById(R.id.take_camera_btn);
        Button cancelBtn = mWindow.findViewById(R.id.cancel_button);

        View closeView = mWindow.findViewById(R.id.close_view);

        closeView.setOnClickListener(v -> dialog.dismiss());
        takePotoBtn.setOnClickListener(v -> {
            pickImageFromAlbum();
            dialog.dismiss();
        });

        takeCameraBtn.setOnClickListener(v -> {
            getPermissions();
            dialog.dismiss();
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        WindowManager windowManager = this.getWindowManager();
        Display d = windowManager.getDefaultDisplay();
        lp.width = (d.getWidth());
        lp.height = (d.getHeight());
        mWindow.setAttributes(lp);
        dialog.show();
    }


    /**
     * 相册
     */
    public void pickImageFromAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(intent, 1);
    }

    /**
     * 拍照
     */
    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File dirFile = new File(SDCARD_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File file = new File(dirFile, "head.jpg");
        if (Build.VERSION.SDK_INT < 24) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        } else {
            Uri uri = FileProvider.getUriForFile(this,
                    "com.chencha.utlisdemo.fileprovider", file);
            // 添加权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        this.startActivityForResult(intent, 2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    makePhoto(data);
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    File dirFile = new File(SDCARD_PATH);
                    if (!dirFile.exists()) {
                        dirFile.mkdir();
                    }
                    File temp = new File(dirFile + "/head.jpg");
                    String fileString;
                    try {
                        Bitmap imageBitmap = BitmapFactory.decodeFile(temp
                                .getAbsolutePath());
                        fileString = bitmapToString(
                                imageBitmap);
                        setPhoto(fileString);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;

        }
    }


    public void makePhoto(Intent data) {
        Bitmap photo = null;
        if (data.getData() != null || data.getExtras() != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String potoUrl = uri.toString();
                if (potoUrl.startsWith("content://")) {
                    try {
                        photo = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (potoUrl.startsWith("file://")) {
                    photo = BitmapFactory.decodeFile(uri.getPath());
                }
            } else {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    photo = (Bitmap) bundle.get("data");
                }
            }
            try {
                File dirFile = new File(SDCARD_PATH);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
                File myCaptureFile = new File(dirFile
                        + "head.jpg");
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(myCaptureFile));
                photo.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                String fileString = bitmapToString(photo);
                setPhoto(fileString);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                // 不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                        GET_PERMISSION_REQUEST);
            }
        } else {
            startCamera();
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            if (PermissionChecker.checkSelfPermission(ImageSelectActivity.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            }
        } else if (requestCode == GET_CALL_PERMISSION_REQUEST) {
            if (PermissionChecker.checkSelfPermission(ImageSelectActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    public static String bitmapToString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bStream);

        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        try {
            bStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
        return string;

    }

    public void setPhoto(String photo) {
        Glide.with(ImageSelectActivity.this).load(photo).into(img);
    }


}
