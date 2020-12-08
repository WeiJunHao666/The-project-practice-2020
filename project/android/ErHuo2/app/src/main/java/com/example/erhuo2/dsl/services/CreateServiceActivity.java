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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.example.erhuo2.dsl.additem.PlusImgsAdapter;
import com.example.erhuo2.dsl.services.entities.ServiceToSer;
import com.example.erhuo2.dsl.services.model.ServiceModel;
import com.example.erhuo2.uploadUtils.Etag;
import com.example.erhuo2.uploadUtils.GetToken;
import com.example.erhuo2.uploadUtils.UploadFile;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateServiceActivity extends AppCompatActivity {

    private TextView create_service_cancel;
    private Button create_service_submit;
    private ArrayList<AlbumFile> mAlbumFiles = new ArrayList<>();
    private List<Bitmap> bitmaps = new ArrayList<>();
    private GridView create_service_imgs;
    private ServiceModel sm = ServiceModel.getInstance();
    private List<File> imgFiles = new ArrayList<>();
    private EditText create_service_content;
    private PlusImgsAdapter adapter;
    private ServiceToSer toPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        getView();

        adapter = new PlusImgsAdapter(this, bitmaps);
        create_service_imgs.setAdapter(adapter);
        create_service_imgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getImg();
            }
        });

        setListener();

    }

    private void setListener() {
        MyListener listener = new MyListener();
        create_service_submit.setOnClickListener(listener);
        create_service_cancel.setOnClickListener(listener);
    }

    private void getView() {
        create_service_cancel = findViewById(R.id.create_service_cancel);
        create_service_submit = findViewById(R.id.create_service_submit);
        create_service_imgs = findViewById(R.id.create_service_imgs);
        create_service_content = findViewById(R.id.create_service_content);

    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.create_service_cancel:
                    onBackPressed();
                    break;
                case R.id.create_service_submit:
                    if(create_service_content.getText().toString().trim().equals("")){
                        Toast.makeText(getApplicationContext(), "发布失败，内容不能为空！",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        getPost();
                        sm.addPost(toPost);
                        Intent i = new Intent();
                        i.setClass(getApplicationContext(),ServePageFragment.class);
                        startActivity(i);
                        break;
                    }
            }
        }
    }

    private void getPost() {
        int userId = 1;
        String content = create_service_content.getText().toString();
        String token = GetToken.getToken("");
        List<String> l = new ArrayList<>();
        UploadFile u = new UploadFile();
        for(File f : imgFiles){
            try {
                String temp = Etag.file(f);
                u.upload(f,temp,token);
                l.add(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置时间的格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        //得到本地时间
        Date date1 = new Date(System.currentTimeMillis());

        String date = dateFormat.format(date1);

        toPost = new ServiceToSer(userId,content,l,date);

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
                        bitmaps.clear();
                        imgFiles.clear();
                        if(result.size()>0){
                            for(int i = 0; i < result.size(); i++){
                                Bitmap bit = BitmapFactory.decodeFile((result.get(i).getPath()));
                                bitmaps.add(bit);
                                File f = new File(result.get(i).getPath());
                                imgFiles.add(f);
                            }
                            adapter.notifyDataSetChanged();
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