package com.example.erhuo2.zsl.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erhuo2.R;
import com.example.erhuo2.zsl.loder.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class CommodityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        Banner banner = (Banner) findViewById(R.id.banner1);
        List images = new ArrayList();
        images.add(R.drawable.aa);
        images.add(R.drawable.bb);
        images.add(R.drawable.cc);
        images.add(R.drawable.dd);
        images.add(R.drawable.ee);
        images.add(R.drawable.ff);
        images.add(R.drawable.gg);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}