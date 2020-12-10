package com.example.erhuo2.zsl.page;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;
import com.example.erhuo2.SearchPageActivity;
import com.example.erhuo2.wjh.allKind.view.AllKindActivity;
import com.example.erhuo2.zsl.activity.ProductDetailsActivity;
import com.example.erhuo2.zsl.adapter.ProductAdapter;
import com.example.erhuo2.zsl.entities.AdvertisingEntity;
import com.example.erhuo2.zsl.entities.ProductEntity;
import com.example.erhuo2.zsl.loder.GlideImageLoader;
import com.example.erhuo2.zsl.view.MyGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

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

public class HomePageFragment extends Fragment {
    private View root;
    private TextView position;
    private Button keyWord;
    private Button search;
    private Button kind1;
    private Button kind2;
    private Button kind3;
    private Button kind4;
    private Button kind5;
    private Button more;
    private Button kind;
    private MyGridView gridView;
    private SmartRefreshLayout refresh_layout;
    private String str;
    private List<AdvertisingEntity> advertisingEntity;
    private ProductAdapter productAdapter;
    private List<ProductEntity> dataSource = new ArrayList<>();

//    private Handler handler = new Handler(){
//        public void handleMessage(Message message){
//            switch (message.what){
//                case 1:
//                    gridView.setAdapter(productAdapter);
//            }
//        }
//    };


    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_page_fragment, container, false);
        upKeyValue("http://10.7.88.15:8081/erhuol/advertisement/all");
        //downloadAdcertising("http://192.168.2.104:8081/erhuol/advertisement/all");

//        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
//        advertisingEntity = rightBean.getDatas();
        List images = new ArrayList();
        images.add(R.drawable.advertising1);
        images.add(R.drawable.advertising2);
        images.add(R.drawable.advertising3);
        images.add(R.drawable.advertising4);
//        images.add(R.drawable.ee);
//        images.add(R.drawable.ff);
//        images.add(R.drawable.gg);
        getViews();
        //获取数据

        getData();

        setOnClickListener();
        productAdapter = new ProductAdapter(getActivity().getApplicationContext(),dataSource, R.layout.home_page_item);
        gridView.setAdapter(productAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra("Img",dataSource.get(i).getImg());
                intent.putExtra("describe",dataSource.get(i).getDescribe());
                intent.putExtra("price",dataSource.get(i).getPrice());
                intent.putExtra("seller",dataSource.get(i).getSeller());
                intent.putExtra("position",dataSource.get(i).getPosition());
                startActivity(intent);
            }
        });

        Banner banner = (Banner) root.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        return root;
    }
    private void getViews(){
        refresh_layout = root.findViewById(R.id.home_refresh);
        position = root.findViewById(R.id.txt_position);
        keyWord = root.findViewById(R.id.btn_search);
//        kind1 = root.findViewById(R.id.btn_kind1);
//        kind2 = root.findViewById(R.id.btn_kind2);
//        kind3 = root.findViewById(R.id.btn_kind3);
//        kind4 = root.findViewById(R.id.btn_kind4);
//        kind5 = root.findViewById(R.id.btn_kind5);
//        more = root.findViewById(R.id.btn_more);
        kind = root.findViewById(R.id.home_kind);
        gridView = root.findViewById(R.id.grid_view);
        Drawable drawable = getResources().getDrawable(R.drawable.zsl_kind);
        drawable.setBounds(10, 0, 70, 70);// 第一0是距左边距离，第二0是距上边距离，60分别是长宽
        kind.setCompoundDrawables(drawable, null, null, null);// 只放左边
    }
    private void setOnClickListener(){
        MyClickListener listener = new MyClickListener();
        keyWord.setOnClickListener(listener);
        kind.setOnClickListener(listener);
//        kind1.setOnClickListener(listener);
//        kind2.setOnClickListener(listener);
//        kind3.setOnClickListener(listener);
//        kind4.setOnClickListener(listener);
//        kind5.setOnClickListener(listener);
//        more.setOnClickListener(listener);

        Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(50, 0, 120, 70);// 第一0是距左边距离，第二0是距上边距离，60分别是长宽
        keyWord.setCompoundDrawables(drawable, null, null, null);// 只放左边
        //给智能刷新控件注册下拉刷新事件监听器
        refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                //通知刷新完毕
                refreshLayout.finishRefresh();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if(dataSource.size()<20) {
                    ProductEntity a = new ProductEntity(R.drawable.dd,"水门水门水门水门水门水门水门水门水门水门水门水门","1000","322宿舍","河北师范大学");
                    dataSource.add(a);
                    dataSource.add(a);
                    dataSource.add(a);
                    dataSource.add(a);
                    dataSource.add(a);
                    dataSource.add(a);
                    //通知加载数据完毕
                    productAdapter = new ProductAdapter(getActivity().getApplicationContext(),dataSource, R.layout.home_page_item);
                    gridView.setAdapter(productAdapter);
                    refresh_layout.finishLoadMore();
                }else{
                    //通知没有更多数据可加载
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }
    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_search:
                    Intent intent = new Intent(getActivity().getApplicationContext(), SearchPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.home_kind:
                    Intent intent6 = new Intent(getActivity().getApplicationContext(), AllKindActivity.class);
                    startActivity(intent6);
                    break;
//                case R.id.btn_kind1:
//                case R.id.btn_kind2:
//                case R.id.btn_kind3:
//                case R.id.btn_kind4:
//                case R.id.btn_kind5:
//                case R.id.btn_more:
//                    Intent intent6 = new Intent(getActivity().getApplicationContext(), AllKindActivity.class);
//                    startActivity(intent6);
//                    break;

            }
        }
    }
    public void getData(){
        ProductEntity s1 = new ProductEntity(R.drawable.product1,"飞科吹风机FH6232大功率可折","49.9","322宿舍","河北师范大学");
        ProductEntity s2 = new ProductEntity(R.drawable.product2,"优衣库 女装 弹力棉质两翻领T恤（长袖）","79","322宿舍","河北师范大学");
        ProductEntity s3 = new ProductEntity(R.drawable.product3,"飞科毛衣服起球修剪充电式衣物剃打刮 ","59","322宿舍","河北师范大学");
        ProductEntity s4 = new ProductEntity(R.drawable.product4,"雅丽黛佳眉笔纤细持久防水不易脱色晕染","47","322宿舍","河北师范大学");
        ProductEntity s5 = new ProductEntity(R.drawable.product5,"恒源祥护膝盖保护套保暖老寒腿男女漆","29","322宿舍","河北师范大学");
        ProductEntity s6 = new ProductEntity(R.drawable.product6,"Skechers斯凯奇冬秋","329","322宿舍","河北师范大学");
        ProductEntity s7 = new ProductEntity(R.drawable.product7,"全新Dior迪奥烈焰蓝金唇","350","322宿舍","河北师范大学");
        ProductEntity s8 = new ProductEntity(R.drawable.product8,"小米手环5智能心率检测蓝牙男女同款运动计","179","322宿舍","河北师范大学");
        ProductEntity s9 = new ProductEntity(R.drawable.product9,"Innisfree/诗悦风吟火","210","322宿舍","河北师范大学");
        ProductEntity s10 = new ProductEntity(R.drawable.product10,"小米充电宝移动电源10000毫安快充超薄","79","322宿舍","河北师范大学");

        dataSource.add(s1);
        dataSource.add(s2);
        dataSource.add(s3);
        dataSource.add(s4);
        dataSource.add(s5);
        dataSource.add(s6);
        dataSource.add(s7);
        dataSource.add(s8);
        dataSource.add(s9);
        dataSource.add(s10);
    }
    private void upKeyValue(final String s){
        new Thread(){
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
                    str = reader.readLine();
                    Log.e("zsl",str+"");
                    advertisingEntity = new Gson().fromJson(str,new TypeToken<List<AdvertisingEntity>>(){}.getType());
                    Log.e("zsl",advertisingEntity.get(0).getId()+"");
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
        }.start();

    }
//      private void downloadAdcertising(final String s){
//          new Thread() {
//            @Override
//            public void run() {
//                //使用网络连接，接收服务端发送的字符串
//                try {
//                    //创建URL对象
//                    URL url = new URL(s);
//                    //获取URLConnection连接对象
//                    URLConnection conn = url.openConnection();
//                    //获取网络输入流
//                    InputStream in = conn.getInputStream();
//                    //使用字符流读取
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(in, "utf-8"));
//                    //读取字符信息
//                    str = reader.readLine();
//                    System.out.println(str);
//                    //listView.setAdapter(myCakeAdapter);
//                    //关闭流
//                    reader.close();
//                    in.close();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//      }
//    private void downloadStr(final String s) {
//        new Thread() {
//            @Override
//            public void run() {
//                //使用网络连接，接收服务端发送的字符串
//                try {
//                    //创建URL对象
//                    URL url = new URL(s);
//                    //获取URLConnection连接对象
//                    URLConnection conn = url.openConnection();
//                    //获取网络输入流
//                    InputStream in = conn.getInputStream();
//                    //使用字符流读取
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(in, "utf-8"));
//                    //读取字符信息
//                    String str = reader.readLine();
//                    System.out.println(str);
//                    String strArray[] = str.split("&&");
//                    for(int i=0;i<strArray.length-3;i+=4){
//                        Map<String,Object> itemData = new HashMap<>();
//                        //itemData.put("name", strArray[i+1]);
//                        itemData.put("describe",strArray[i+1]);
//                        itemData.put("price","￥"+strArray[i+2]);
//                        itemData.put("position",strArray[i+3]);
//                        URL url1 = new URL(Util.SERVER_ADDR+strArray[i]);
//                        System.out.println(Util.SERVER_ADDR+strArray[i]);
//                        InputStream inputStream = url1.openStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        itemData.put("img",bitmap);
//                        System.out.print(itemData);
//                        dataSource.add(itemData);
//                        //绑定adapter
//                        productAdapter = new ProductAdapter(getActivity().getApplicationContext(),
//                                dataSource,
//                                R.layout.home_page_item);
//                        Message message = new Message();
//                        message.what = 1;
//                        handler.sendMessage(message);
//                    }
//
//                    //listView.setAdapter(myCakeAdapter);
//                    //关闭流
//                    reader.close();
//                    in.close();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }

}
