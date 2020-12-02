package com.example.erhuo2.wjh.allKind;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
public class AllKindActivity extends AppCompatActivity implements AllKindLeftAdapter.OnSelectorListener{
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private AllKindLeftAdapter adapter;
    private AllKindRightAdapter adapter2;
    private List<String> list2;
    private List<ThemeMainReq.DatasBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kind);
        recyclerView = findViewById(R.id.recyclerView_title);
//        recyclerView2 = findViewById(R.id.recyclerView_content);
//        list2 = new ArrayList<>();
//        for(int j=0; j<25; j++){
//            list2.add("你好");
//        }
//        adapter2 = new AllKindRightAdapter(this, list2);
//        LinearLayoutManager manager1 = new LinearLayoutManager(this);
//        manager1.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView2.setLayoutManager(manager1);
//        recyclerView2.setAdapter(adapter2);
        initData();
    }
    private void initData() {
        //模拟数据
        String response = "{\"datas\": [{\"id\": \"56\",\"showName\": \"清新\"},{\"id\": \"57\",\"showName\": \"复古\"},{\"id\": \"58\", \"showName\": \"古风\"},{\"id\": \"59\", \"showName\": \"盐系\"},{ \"id\": \"141\", \"showName\": \"暗黑\"},{  \"id\": \"62\", \"showName\": \"花草\"},{\"id\": \"63\", \"showName\": \"\n" +
                "美食物品\"},{ \"id\": \"64\", \"showName\": \"人物\" },{ \"id\": \"65\",  \"showName\": \"文字字母\"},{\"id\": \"67\", \"showName\": \"基础款\"},{\"id\": \"68\",\"showName\": \"风景\"},{ \"id\": \"70\", \"showName\": \"动物\"}, { \"id\": \"70\", \"showName\": \"英语\"}, { \"id\": \"70\", \"showName\": \"1物\"}, { \"id\": \"70\", \"showName\": \"xx\"}, { \"id\": \"70\", \"showName\": \"tt\"} ,{ \"id\": \"70\", \"showName\": \"kk\"}]}\n";
        //对数据进行解析
        ThemeMainReq themeMainReq = new Gson().fromJson(response, ThemeMainReq.class);
        //获取分类集合
        list = themeMainReq.getDatas();
        //数据处理
        adapter = new AllKindLeftAdapter(this, list);
        recyclerView.setAdapter(adapter);
        //默认选中
        list.get(0).setChick(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter.setOnSelectorListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelect(View view, int position) {
        //选中处理
        ThemeMainReq.DatasBean datasBean = list.get(position);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getShowName().equals(datasBean.getShowName())) {
                list.get(i).setChick(true);
            } else {
                list.get(i).setChick(false);
            }
        }
        for(int i=0; i<list.size(); i++){
            Log.e(i+1+"", list.get(i).isChick()+"");
        }
        //刷新列表
        adapter.notifyDataSetChanged();

    }
}