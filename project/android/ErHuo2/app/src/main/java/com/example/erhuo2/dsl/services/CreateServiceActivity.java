package com.example.erhuo2.dsl.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateServiceActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE = 100;//定义请求码常量
    private TextView create_service_cancel;
    private ImageView create_service_getimg;
    private RelativeLayout border_getimg;
    private Button create_service_submit;
    private ArrayList<AlbumFile> mAlbumFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        getView();

        setListener();

    }

    private void setListener() {
        MyListener listener = new MyListener();
        create_service_submit.setOnClickListener(listener);
        create_service_getimg.setOnClickListener(listener);
        create_service_cancel.setOnClickListener(listener);
    }

    private void getView() {
        create_service_cancel = findViewById(R.id.create_service_cancel);
        create_service_getimg = findViewById(R.id.create_service_getimg);
        border_getimg = findViewById(R.id.border_getimg);
        create_service_submit = findViewById(R.id.create_service_submit);

    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.create_service_cancel:
                    onBackPressed();
                    break;
                case R.id.create_service_getimg:
                    getImg();
                    break;
                case R.id.create_service_submit:

                    break;
            }
        }
    }

    private void getImg() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());

        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(6)
                .checkedList(mAlbumFiles)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        Log.e("dsl",result.size()+"");
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Log.e("dsl","222");
                    }
                })
                .start();


    }

    public class MediaLoader implements AlbumLoader {

        @Override
        public void load(ImageView imageView, AlbumFile albumFile) {
            load(imageView, albumFile.getPath());
        }

        @Override
        public void load(ImageView imageView, String url) {
            Log.e("dsl",url);
            Glide.with(imageView.getContext())
                    .load(url)
                    .error(R.drawable.write)
                    .placeholder(R.drawable.back)
                    .crossFade()
                    .into(imageView);
        }
    }


}