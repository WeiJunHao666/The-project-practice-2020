package com.example.erhuo2.dsl.additem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.CreateServiceActivity;
import com.example.erhuo2.dsl.services.view.SquareImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.impl.OnItemClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {
    private ArrayList<AlbumFile> mAlbumFiles = new ArrayList<>();
    private List<Bitmap> imgsList = new ArrayList<>();
    private GridView mGridView;
    private PlusImgsAdapter adapter;
    private List<File> imgFiles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mGridView = findViewById(R.id.add_item_imgs);


        adapter = new PlusImgsAdapter(this, imgsList);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getImg();
            }
        });
    }


    private void getImg() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new AddItemActivity.MediaLoader())
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
                        imgsList.clear();
                        for(int i = 0; i < result.size(); i++){
                            Bitmap bit = BitmapFactory.decodeFile((result.get(i).getPath()));
                            imgsList.add(bit);
                            File f = new File(result.get(i).getPath());
                            imgFiles.add(f);
                        }
                        adapter.notifyDataSetChanged();
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