package com.example.erhuo2.zsl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.example.erhuo2.SearchPageActivity;
import com.example.erhuo2.zsl.adapter.ProductAdapter;
import com.example.erhuo2.zsl.adapter.ViewPagerAdapter;
import com.example.erhuo2.zsl.entities.ProductEntity;
import com.example.erhuo2.zsl.entities.ProductImgEntity;
import com.example.erhuo2.zsl.view.PhotoPageTransformer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView imgBack;
    private ImageView imgSearch;
    private Button btnChat;
    private CircleImageView sellerCircleImg1;
    private CircleImageView sellerCircleImg2;
    private ProductEntity productEntities;
    private TextView value;
    private TextView describe;
    private TextView sellerName;
    private ViewPager viewPager;
    private RelativeLayout viewPagerBox;
    private ViewPagerAdapter viewPagerAdapter;
    private String id;
    private Banner banner;
    private ImageView imageView;
    private List<ImageView> imageViews = new ArrayList<>();
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("zsl",id+"");
//        id = Integer.parseInt(intent.getStringExtra("id"));


        downProduct("http://10.7.89.236:8081/erhuol/commodity/one"+"?id="+id);
        getViews();
        setOnClickListener();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Glide.with(ProductDetailsActivity.this)
                .load("http://"+productEntities.getUser().getImg())
                .error(R.drawable.img_error)
                .into(sellerCircleImg1);
        Glide.with(ProductDetailsActivity.this)
                .load("http://"+productEntities.getUser().getImg())
                .error(R.drawable.img_error)
                .into(sellerCircleImg2);

        describe.setText(productEntities.getDescribe());
        value.setText(productEntities.getValue());
        sellerName.setText(productEntities.getUser().getNickname());

        Log.e("lll",productEntities+"");
        banner.setImageLoader(new ImageLoader(){

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                for(int i=0;i<productEntities.getImg().size();i++){

                    Glide.with(ProductDetailsActivity.this)
                            .load("http://"+productEntities.getImg().get(i).getImg())
                            .error(R.drawable.img_error)
                            .into(imageView);
                    Log.e("xxx",productEntities.getImg().get(i).getImg());
                    imageViews.add(imageView);
                }
            }
        });
        for(int i=0;i<productEntities.getImg().size();i++){

            Glide.with(ProductDetailsActivity.this)
                    .load("http://"+productEntities.getImg().get(i).getImg())
                    .error(R.drawable.img_error)
                    .into(imageView);
            Log.e("xxx",productEntities.getImg().get(i).getImg());
            imageViews.add(imageView);
        }
//        banner.setBannerAnimation(Transformer.Accordion);
        banner.setImages(imageViews);//设置图片资源
        //设置指示器位置（即图片下面的那个小圆点）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setDelayTime(1000000000);//设置轮播时间3秒切换下一图
        banner.start();//开始进行banner渲染

    }
    private void getViews(){
        imageView = findViewById(R.id.details_img);
        sellerName = findViewById(R.id.details_seller_name);
        sellerCircleImg1 = findViewById(R.id.details_seller_img1);
        sellerCircleImg2 = findViewById(R.id.details_seller_img2);
        value = findViewById(R.id.details_price);
        describe = findViewById(R.id.details_describe);
        imgBack = findViewById(R.id.details_back);
        imgSearch = findViewById(R.id.details_search);
        btnChat = findViewById(R.id.details_chat);
        viewPagerBox = findViewById(R.id.product_viewpager_box);
        banner = findViewById(R.id.product_details_banner);



    }
    private void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        imgBack.setOnClickListener(listener);
        imgSearch.setOnClickListener(listener);
        btnChat.setOnClickListener(listener);
    }
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.details_back:
//                    finish();
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_in_right,
                            R.anim.activity_out_right);
                    break;
                case R.id.details_search:
                    Intent intent = new Intent(ProductDetailsActivity.this, SearchPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.details_chat:
                    //Intent intent1 = new Intent(ProductDetailsActivity.this,)
            }
        }
    }
    private void downProduct(final String s){
        thread = new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(s);
                    Log.e("kk", s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置网络请求的方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    //必须获取网络输入流，保证客户端和服务端建立连接
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    //读取字符信息
                    String str = reader.readLine();
                    Log.e("sss",str+"");
                    productEntities = new Gson().fromJson(str,new TypeToken<ProductEntity>(){}.getType());
                    Log.e("zzz",productEntities+"");

                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
//                    if(flag.equals("usernameError")){
//                        loginListener.onFailure("请输入正确的用户名");
//                    }else if(flag.equals("passwordError")){
//                        loginListener.onFailure("密码错误");
//                    }else{
//                        loginListener.onSuccess(flag);
//                    }
                } catch (MalformedURLException | ProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}