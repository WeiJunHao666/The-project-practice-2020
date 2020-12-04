package com.example.erhuo2.wjh.allKind.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;
import com.example.erhuo2.wjh.allKind.adapter.AllKindLeftAdapter;
import com.example.erhuo2.wjh.allKind.adapter.AllKindRightAdapter;
import com.example.erhuo2.wjh.allKind.bean.LeftBean;
import com.example.erhuo2.wjh.allKind.bean.RightBean;
import com.google.gson.Gson;

import java.util.List;


public class AllKindActivity extends AppCompatActivity implements AllKindLeftAdapter.OnSelectorListener,KindView {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private AllKindLeftAdapter adapter;
    private AllKindRightAdapter adapter2;
    private List<LeftBean.DatasBean> list;
    private List<RightBean.DatasBean> list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kind);
        recyclerView = findViewById(R.id.recyclerView_left);
        recyclerView2 = findViewById(R.id.recyclerView_right);
        initData();
    }
    private void initData() {
        //模拟数据
        String response = "{\"datas\": [{\"id\": \"56\",\"showName\": \"清新\"},{\"id\": \"57\",\"showName\": \"复古\"},{\"id\": \"58\", \"showName\": \"古风\"},{\"id\": \"59\", \"showName\": \"盐系\"},{ \"id\": \"141\", \"showName\": \"暗黑\"},{  \"id\": \"62\", \"showName\": \"花草\"},{\"id\": \"63\", \"showName\": \"\n" +
                "美食物品\"},{ \"id\": \"64\", \"showName\": \"人物\" },{ \"id\": \"65\",  \"showName\": \"文字字母\"},{\"id\": \"67\", \"showName\": \"基础款\"},{\"id\": \"68\",\"showName\": \"风景\"},{ \"id\": \"70\", \"showName\": \"动物\"}, { \"id\": \"70\", \"showName\": \"英语\"}, { \"id\": \"70\", \"showName\": \"1物\"}, { \"id\": \"70\", \"showName\": \"xx\"}, { \"id\": \"70\", \"showName\": \"tt\"} ,{ \"id\": \"70\", \"showName\": \"kk\"}]}\n";
        //对数据进行解析
        LeftBean leftBean = new Gson().fromJson(response, LeftBean.class);
        //获取分类集合
        list = leftBean.getDatas();
        //数据处理
        adapter = new AllKindLeftAdapter(this, list);
        //默认选中
        list.get(0).setChick(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter.setOnSelectorListener(this);
        recyclerView.setAdapter(adapter);
        initData1();
    }

    private void initData1(){
        //模拟数据
        String str = "{\"datas\":[{\"title\": \"电器\", \"list\":[{\"name\":\"吸尘器\"}, {\"name\":\"热水器\"}, " +
                " {\"name\":\"电热水器\"},{\"name\":\"宿舍影院\"}, {\"name\":\"美的\"}, {\"name\":\"空调扇\"}]}, {\"title\": \"生活电器\", \"list\":[{\"name\":\"音响\"},  {\"name\":\"蓝牙音箱\"},  {\"name\":\"小米\"}, {\"name\":\"组合音响\"}, {\"name\":\"加湿器\"}]}, {\"title\": \"办公用品\", \"list\":[{\"name\":\"办公纸制品\"}, {\"name\":\"三星\"},  {\"name\":\"索尼\"},  {\"name\":\"钢笔\"}, {\"name\":\"抽纸\"}]}" +
                ",{\"title\": \"保健护理\", \"list\":[{\"name\":\"松下\"}, {\"name\":\"电子美容仪\"}, {\"name\":\"瘦身带\"}, {\"name\":\"眼部按摩仪\"}, {\"name\":\"南极人\"}, {\"name\":\"足浴器\"}]},{\"title\": \"古董收藏\", \"list\":[{\"name\":\"古币\"}, {\"name\":\"奇石\"},  {\"name\":\"老玉\"}]}]}";
        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
        list2 = rightBean.getDatas();
        adapter2 = new AllKindRightAdapter(this, list2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(adapter2);

    }
    private void initData2(){
        //模拟数据
        String str = "{\"datas\":[{\"title\": \"精选手机\", \"list\":[{\"name\":\"华为\"}, {\"name\":\"vivo\"}, " +
                "{\"name\":\"小米\"}, {\"name\":\"OPPO\"}]}, {\"title\": \"智能设备及3c配件\", \"list\":[{\"name\":\"蓝牙耳机\"}, {\"name\":\"连接线\"},  {\"name\":\"电池\"},  {\"name\":\"移动电源\"}, {\"name\":\"手环\"}" +
                ",{\"name\":\"保护膜\"}, {\"name\":\"手机数据线\"}]}, {\"title\": \"电脑配件\", \"list\":[{\"name\":\"苹果\"}, {\"name\":\"鼠标\"},  {\"name\":\"键盘\"},  {\"name\":\"主板\"}, {\"name\":\"内存\"}, {\"name\":\"机械硬盘\"}, {\"name\":\"主板\"}" +
                ", {\"name\":\"无线鼠标\"}, {\"name\":\"LED显示屏\"}, {\"name\":\"散热器\"}]}" + ",{\"title\": \"电脑\", \"list\":[{\"name\":\"苹果\"}, {\"name\":\"一体机\"}, {\"name\":\"台式机\"}, {\"name\":\"笔记本电脑\"},{\"name\":\"平板电脑\"}]}," +
                "{\"title\": \"相机\", \"list\":[{\"name\":\"照相机\"}, {\"name\":\"镜头\"}, {\"name\":\"单反镜头\"}, {\"name\":\"数码单反\"}, {\"name\":\"数码相机\"}, {\"name\":\"索尼\"}]}]}";
        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
        list2 = rightBean.getDatas();
        adapter2 = new AllKindRightAdapter(this, list2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(adapter2);

    }

    @Override
    public void onSelect(View view, int position) {
        //选中处理
        LeftBean.DatasBean datasBean = list.get(position);
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
        String id = list.get(position).getId();
        if(id.equals("57")){
            initData2();
        }
        if(id.equals("56")){
            initData1();
        }
        //刷新列表
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(List<RightBean.DatasBean> list) {

    }

    @Override
    public void onFailure(String msg) {

    }
}