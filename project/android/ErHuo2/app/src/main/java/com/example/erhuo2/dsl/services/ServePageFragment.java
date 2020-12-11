package com.example.erhuo2.dsl.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.example.erhuo2.dsl.services.presenter.GetPostPresenter;
import com.example.erhuo2.dsl.services.view.GetPostView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ServePageFragment extends Fragment implements GetPostView {
    private View root;
    private ImageView services_write;
    private List<ServiceEntity> li = new ArrayList<>();
    private List<ServiceEntity> list = new ArrayList<>();
    private SmartRefreshLayout refresh_layout;
    private ServiceModel sm = new ServiceModel().getInstance();
    private GetPostPresenter presenter = new GetPostPresenter(this);
    private ServicesAdapter adapter;
    ListView lv_words;
    private int pageNum = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                li = (List<ServiceEntity>)msg.obj;

                list.addAll(li);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.serve_page_fragment, container, false);

        findView();

        setLintener();

        //获取数据
        getData();

        //绑定Adapter
        adapter = new ServicesAdapter(getActivity().getApplicationContext(),
                list, R.layout.service_list);
        lv_words.setAdapter(adapter);

        //查看详情跳转
        lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("postId",list.get(position).getPostId());
                i.putExtra("userId",list.get(position).getUserId());
                i.putExtra("name",list.get(position).getName());
                i.putExtra("img",""+list.get(position).getImg());
                i.putExtra("check",list.get(position).isCheck()+"");
                i.putExtra("content", list.get(position).getContent());
                i.putStringArrayListExtra("imgs",list.get(position).getImgs());
                i.putExtra("pageview",list.get(position).getPageview());
                i.putExtra("date",list.get(position).getDate());
                i.putExtra("prizes",list.get(position).getPrizes());
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
                pageNum = 1;
                list.clear();
                presenter.getData(pageNum);

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
                Log.e("dsl","重新加载"+pageNum);
                li.clear();
                presenter.getData(pageNum);
                if(li.size()>0){
                    adapter.notifyDataSetChanged();
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
        list.clear();
        presenter.getData(pageNum);
    }

    private void findView() {
        services_write = root.findViewById(R.id.services_write);
        refresh_layout = root.findViewById(R.id.service_refresh);
        lv_words = root.findViewById(R.id.show_all_services);
    }

    @Override
    public void onSuccess(List<ServiceEntity> list1) {
        Message msg = handler.obtainMessage();
        msg.obj = list1;
        msg.what = 1;
        handler.sendMessage(msg);
    }

    @Override
    public void onFailed(String s) {

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