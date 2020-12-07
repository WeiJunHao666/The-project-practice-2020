package com.example.erhuo2.dsl.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.adapter.ServicesAdapter;
import com.example.erhuo2.dsl.services.entities.ServiceEntity;
import com.example.erhuo2.dsl.services.model.ServiceModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ServePageFragment extends Fragment {
    private View root;
    private ImageView services_write;
    private List<ServiceEntity> list = new ArrayList<>();
    private SmartRefreshLayout refresh_layout;
    private ServiceModel sm = new ServiceModel().getInstance();
    private ServicesAdapter adapter;
    private int pageNum = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.serve_page_fragment, container, false);

        findView();

        setLintener();

        //获取数据
        getData();

        //绑定Adapter
        ListView lv_words = root.findViewById(R.id.show_all_services);
        adapter = new ServicesAdapter(getActivity().getApplicationContext(),
                list, R.layout.service_list);
        lv_words.setAdapter(adapter);

        //查看详情跳转
        lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("name",list.get(position).getName());
                i.putExtra("img",""+list.get(position).getImg());
                i.putExtra("check",list.get(position).getCheck());
                i.putExtra("content", list.get(position).getContent());
                i.putIntegerArrayListExtra("imgs",list.get(position).getImgs());
                i.setClass(getActivity().getApplicationContext(), ViewServiceActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    private void setLintener() {
        MyListener listener = new MyListener();
        services_write.setOnClickListener(listener);

        //给智能刷新控件注册下拉刷新事件监听器
        refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //重新加载
                //list = sm.getServiceDate(0);
                //adapter.notifyDataSetChanged();

                //通知刷新完毕
                refreshLayout.finishRefresh();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                //请求分页数据
//                List<ServiceEntity> li = new ArrayList<>();
//                li = sm.getServiceDate(pageNum);
//                if(li.size()>0){
//                    list.addAll(0,li);
//                    //通知加载数据完毕
//                    refresh_layout.finishLoadMore();
//                }else{
//                    //通知没有更多数据可加载
//                    refreshLayout.finishLoadMoreWithNoMoreData();
//                }

                if(list.size()<10) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(R.drawable.ser2_1);
                    l.add(R.drawable.ser2_2);
                    l.add(R.drawable.ser2_3);
                    l.add(R.drawable.ser2_4);
                    ServiceEntity a = new ServiceEntity(R.drawable.b,"dsl","苹果7。32 G.想换新手机了，充电很快。电池效率98%没有基带。没有摄像头ID可退出很好用。可小刀。大刀勿来！\n",false,10,l);
                    ArrayList<Integer> i = new ArrayList<>();
                    i.add(R.drawable.ser3_1);
                    i.add(R.drawable.ser3_2);
                    ServiceEntity b = new ServiceEntity(R.drawable.c,"wjh","十成新，只戴了一分钟，感觉颜色不符合我\n" +
                            "感兴趣的话点“我想要”和我私聊吧～\n",false,0,i);
                    ArrayList<Integer> s = new ArrayList<>();
                    s.add(R.drawable.ser4_1);
                    ServiceEntity c = new ServiceEntity(R.drawable.d,"ywh","牧马人一代g60,自用没有暗病，鼠标多用的时间不长，手掌处有磨损。售出不退，离的远的不包邮。\n",false,1,s);
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    //通知加载数据完毕
                    refresh_layout.finishLoadMore();
                }else{
                    //通知没有更多数据可加载
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });

    }

    private void getData() {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(R.drawable.ser1_1);
        l.add(R.drawable.ser1_2);
        l.add(R.drawable.ser1_3);
        ServiceEntity s1 = new ServiceEntity(R.drawable.a,"zsl","达尔优MINI版，小巧方便。\n" +
                " 手感不错，支持官方驱动和宏，\n" +
                "成色如图 有黑白两色。五色变光，办公，游戏必备。   \n" +
                "鼠标都是自己动手改的， 与网吧直接淘汰的不同，本鼠标换了\n" +
                "全新欧姆龙机械寿命两千万次的微动， \n" +
                "全新USB线， ^_^\n" +
                "全新脚贴。 \n" +
                "本鼠标全部进行了深度清理。   \n" +
                "发货之前全部酒精消毒，七天之内有质量问题包退换， 保修三个月。\n",false,12,l);
        ArrayList<Integer> i = new ArrayList<>();
        i.add(R.drawable.ser2_1);
        i.add(R.drawable.ser2_2);
        i.add(R.drawable.ser2_3);
        i.add(R.drawable.ser2_4);
        ServiceEntity s2 = new ServiceEntity(R.drawable.b,"dsl","苹果7。32 G.想换新手机了，充电很快。电池效率98%没有基带。没有摄像头ID可退出很好用。可小刀。大刀勿来！\n",false,10,i);
        ArrayList<Integer> s = new ArrayList<>();
        s.add(R.drawable.ser3_1);
        s.add(R.drawable.ser3_2);
        ServiceEntity s3 = new ServiceEntity(R.drawable.c,"wjh","十成新，只戴了一分钟，感觉颜色不符合我\n" +
                "感兴趣的话和我私聊吧～\n",false,0,s);
        ArrayList<Integer> t = new ArrayList<>();
        t.add(R.drawable.ser4_1);
        ServiceEntity s4 = new ServiceEntity(R.drawable.d,"ywh","牧马人一代g60,自用没有暗病，鼠标多用的时间不长，手掌处有磨损。售出不退，离的远的不包邮。\n",false,1,t);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);


        //list = sm.getServiceDate(0);

    }

    private void findView() {
        services_write = root.findViewById(R.id.services_write);
        refresh_layout = root.findViewById(R.id.service_refresh);
    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.services_write:
                    Intent i = new Intent();
                    i.setClass(getContext(), CreateServiceActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }
}