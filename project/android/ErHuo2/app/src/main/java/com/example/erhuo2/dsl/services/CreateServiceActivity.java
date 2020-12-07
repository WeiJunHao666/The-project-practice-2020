package com.example.erhuo2.dsl.services;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.model.ServiceModel;
import com.example.erhuo2.dsl.services.view.SquareImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

import java.util.ArrayList;
import java.util.List;

public class CreateServiceActivity extends AppCompatActivity {

    private TextView create_service_cancel;
    private ImageView create_service_getimg;
    private Button create_service_submit;
    private ArrayList<AlbumFile> mAlbumFiles = new ArrayList<>();
    private List<Bitmap> bitmaps = new ArrayList<>();
    private GridLayout create_service_imgs;
    private ServiceModel sm = ServiceModel.getInstance();

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
        create_service_submit = findViewById(R.id.create_service_submit);
        create_service_imgs = findViewById(R.id.create_service_imgs);

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
                    //sm.addPost();
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(),ServePageFragment.class);
                    startActivity(i);
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
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        create_service_imgs.removeAllViews();
                        bitmaps.clear();
                        if(result.size()>0){
                            create_service_imgs.setVisibility(View.VISIBLE);
                            for(int i = 0; i < result.size(); i++){
                                Bitmap bit = BitmapFactory.decodeFile((result.get(i).getPath()));
                                bitmaps.add(bit);

                                GridLayout.Spec rowSpec = GridLayout.spec(i/3);//行数
                                GridLayout.Spec columnSpec = GridLayout.spec(i%3, 1.0f);//列数 列宽的比例 weight=1
                                ImageView imageView = new SquareImageView(getApplicationContext());
                                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
                                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
                                layoutParams.rowSpec = rowSpec;
                                layoutParams.columnSpec = columnSpec;

                                layoutParams.setMargins(5, 5, 5, 5);

                                imageView.setImageBitmap(bit);
                                create_service_imgs.addView(imageView, layoutParams);
                            }
                        }
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
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