package com.example.erhuo2.zsl.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;
import com.example.erhuo2.SearchPageActivity;
import com.example.erhuo2.wjh.allKind.AllKindActivity;
import com.example.erhuo2.zsl.adapter.ProductAdapter;
import com.example.erhuo2.zsl.entities.ProductEntity;
import com.example.erhuo2.zsl.loder.GlideImageLoader;
import com.example.erhuo2.zsl.view.MyGridView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageFragment extends Fragment {
    private View root;
    private TextView position;
    private EditText keyWord;
    private Button search;
    private Button kind1;
    private Button kind2;
    private Button kind3;
    private Button kind4;
    private Button kind5;
    private Button more;
    private MyGridView gridView;
   // private RefreshableView refreshableView;
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


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_page_fragment, container, false);

        List images = new ArrayList();
        images.add(R.drawable.aa);
        images.add(R.drawable.bb);
        images.add(R.drawable.cc);
        images.add(R.drawable.dd);
        images.add(R.drawable.ee);
        images.add(R.drawable.ff);
        images.add(R.drawable.gg);
        getViews();
        //获取数据
        getData();
        productAdapter = new ProductAdapter(getActivity().getApplicationContext(),dataSource, R.layout.home_page_item);
        gridView.setAdapter(productAdapter);
        setOnClickListener();
        //downloadStr(Util.SERVER_ADDR+"HomeFragmentServlet");
//        refreshableView = (RefreshableView) root.findViewById(R.id.refreshable_view);
//        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                refreshableView.finishRefreshing();
//            }
//        }, 0);
        Banner banner = (Banner) root.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        Map<String,Object> map1 = new HashMap<>();

        return root;
    }
    private void getViews(){
        position = root.findViewById(R.id.txt_position);
        keyWord = root.findViewById(R.id.edit_keyword);
        search = root.findViewById(R.id.btn_search);
        kind1 = root.findViewById(R.id.btn_kind1);
        kind2 = root.findViewById(R.id.btn_kind2);
        kind3 = root.findViewById(R.id.btn_kind3);
        kind4 = root.findViewById(R.id.btn_kind4);
        kind5 = root.findViewById(R.id.btn_kind5);
        more = root.findViewById(R.id.btn_more);
        gridView = root.findViewById(R.id.grid_view);
    }
    public void setOnClickListener(){
        MyClickListener listener = new MyClickListener();
        keyWord.setOnClickListener(listener);
        search.setOnClickListener(listener);
        kind1.setOnClickListener(listener);
        kind2.setOnClickListener(listener);
        kind3.setOnClickListener(listener);
        kind4.setOnClickListener(listener);
        kind5.setOnClickListener(listener);
        more.setOnClickListener(listener);
    }
    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.edit_keyword:
                case R.id.btn_search:
                    Intent intent = new Intent(getActivity().getApplicationContext(), SearchPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_kind1:
                case R.id.btn_kind2:
                case R.id.btn_kind3:
                case R.id.btn_kind4:
                case R.id.btn_kind5:
                case R.id.btn_more:
                    Intent intent6 = new Intent(getActivity().getApplicationContext(), AllKindActivity.class);
                    startActivity(intent6);
                    break;

            }
        }
    }
    public void getData(){
        ProductEntity s1 = new ProductEntity(R.drawable.ff,"水门水门水门水门水门水门水门水门水门水门水门水门","1000","322宿舍","河北师范大学");
        dataSource.add(s1);
        dataSource.add(s1);
        dataSource.add(s1);
        dataSource.add(s1);
        dataSource.add(s1);
        dataSource.add(s1);
        dataSource.add(s1);
    }
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
