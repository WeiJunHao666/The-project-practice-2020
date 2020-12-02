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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.serve_page_fragment, container, false);

        findView();

        setLintener();

        //获取数据
        getData();

        //绑定Adapter
        ListView lv_words = root.findViewById(R.id.show_all_services);
        ServicesAdapter adapter = new ServicesAdapter(getActivity().getApplicationContext(),
                list, R.layout.service_list);
        lv_words.setAdapter(adapter);

        //查看详情跳转
        lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("name",list.get(position).getName());
                i.putExtra("img",""+list.get(position).getImg());
                //i.putExtra("content", list.get(position).getContent());
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

                //通知刷新完毕
                refreshLayout.finishRefresh();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if(list.size()<10) {
                    ServiceEntity a = new ServiceEntity(R.drawable.write, "123", "aaa", false,123);
                    ServiceEntity b = new ServiceEntity(R.drawable.write, "456", "aaa", false,123);
                    ServiceEntity c = new ServiceEntity(R.drawable.write, "789", "aaa", false,123);
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
        ServiceEntity s1 = new ServiceEntity(R.drawable.first,"张树林","123",false,123);
        ServiceEntity s2 = new ServiceEntity(R.drawable.second,"树林张","123",false,123);
        ServiceEntity s3 = new ServiceEntity(R.drawable.first,"张树林","123",false,123);
        ServiceEntity s4 = new ServiceEntity(R.drawable.second,"树林张","123",false,123);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
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

                    break;
            }
        }
    }
}