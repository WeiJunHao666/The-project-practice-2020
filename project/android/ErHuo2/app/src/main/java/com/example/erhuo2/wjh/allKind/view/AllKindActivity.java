package com.example.erhuo2.wjh.allKind.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;
import com.example.erhuo2.wjh.ConfigUtil;
import com.example.erhuo2.wjh.allKind.adapter.AllKindLeftAdapter;
import com.example.erhuo2.wjh.allKind.adapter.AllKindRightAdapter;
import com.example.erhuo2.wjh.allKind.bean.LeftBean;
import com.example.erhuo2.wjh.allKind.bean.RightBean;
import com.example.erhuo2.wjh.allKind.bean.TitleBean;
import com.example.erhuo2.wjh.allKind.presenter.KindPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class AllKindActivity extends AppCompatActivity implements AllKindLeftAdapter.OnSelectorListener,KindView {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private AllKindLeftAdapter adapter;
    private AllKindRightAdapter adapter2;
    private List<LeftBean> list;
    private List<TitleBean>list2 = new ArrayList<>();
    private List<RightBean> list3;
    private KindPresenter presenter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String str = (String) msg.obj;
                    //对数据进行解析
                    list = new Gson().fromJson(str, new TypeToken<List<LeftBean>>(){}.getType());
                    //数据处理
                    adapter = new AllKindLeftAdapter(getApplicationContext(), list);
                    //默认选中
                    list.get(0).setChick(true);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    initData();
                    list3 = list.get(0).getList();
                    Log.e("size", list2.size()+"");
                    adapter.setOnSelectorListener(AllKindActivity.this);
                    adapter2 = new AllKindRightAdapter(getApplicationContext(), list2, list3);
                    LinearLayoutManager manager2 = new LinearLayoutManager(getApplicationContext());
                    manager2.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView2.setLayoutManager(manager2);
                    recyclerView2.setAdapter(adapter2);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kind);
        recyclerView = findViewById(R.id.recyclerView_left);
        recyclerView2 = findViewById(R.id.recyclerView_right);
        //initData();
        presenter = new KindPresenter(this);
        presenter.showRight(ConfigUtil.ALLKIND);
    }
    private void initData() {
        String []name = {"美容护肤"};
        List<String> l = new ArrayList<>();
        l.add(name[0]);
        TitleBean t1 = new TitleBean(1, l);
        list2.add(t1);
    }

    private void initData1(int position){
        //模拟数据
//        String str = "{\"datas\":[{\"title\": \"电器\", \"list\":[{\"name\":\"吸尘器\"}, {\"name\":\"热水器\"}, " +
//                " {\"name\":\"电热水器\"},{\"name\":\"宿舍影院\"}, {\"name\":\"美的\"}, {\"name\":\"空调扇\"}]}, {\"title\": \"生活电器\", \"list\":[{\"name\":\"音响\"},  {\"name\":\"蓝牙音箱\"},  {\"name\":\"小米\"}, {\"name\":\"组合音响\"}, {\"name\":\"加湿器\"}]}, {\"title\": \"办公用品\", \"list\":[{\"name\":\"办公纸制品\"}, {\"name\":\"三星\"},  {\"name\":\"索尼\"},  {\"name\":\"钢笔\"}, {\"name\":\"抽纸\"}]}" +
//                ",{\"title\": \"保健护理\", \"list\":[{\"name\":\"松下\"}, {\"name\":\"电子美容仪\"}, {\"name\":\"瘦身带\"}, {\"name\":\"眼部按摩仪\"}, {\"name\":\"南极人\"}, {\"name\":\"足浴器\"}]},{\"title\": \"古董收藏\", \"list\":[{\"name\":\"古币\"}, {\"name\":\"奇石\"},  {\"name\":\"老玉\"}]}]}";
//        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
//        list2 = rightBean.getDatas();
        list3 = list.get(position).getList();
        adapter2 = new AllKindRightAdapter(this, list2, list3);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(adapter2);

    }
//    private void initData2(){
//        //模拟数据
//        String str = "{\"datas\":[{\"title\": \"精选手机\", \"list\":[{\"name\":\"华为\"}, {\"name\":\"vivo\"}, " +
//                "{\"name\":\"小米\"}, {\"name\":\"OPPO\"}]}, {\"title\": \"智能设备及3c配件\", \"list\":[{\"name\":\"蓝牙耳机\"}, {\"name\":\"连接线\"},  {\"name\":\"电池\"},  {\"name\":\"移动电源\"}, {\"name\":\"手环\"}" +
//                ",{\"name\":\"保护膜\"}, {\"name\":\"手机数据线\"}]}, {\"title\": \"电脑配件\", \"list\":[{\"name\":\"苹果\"}, {\"name\":\"鼠标\"},  {\"name\":\"键盘\"},  {\"name\":\"主板\"}, {\"name\":\"内存\"}, {\"name\":\"机械硬盘\"}, {\"name\":\"主板\"}" +
//                ", {\"name\":\"无线鼠标\"}, {\"name\":\"LED显示屏\"}, {\"name\":\"散热器\"}]}" + ",{\"title\": \"电脑\", \"list\":[{\"name\":\"苹果\"}, {\"name\":\"一体机\"}, {\"name\":\"台式机\"}, {\"name\":\"笔记本电脑\"},{\"name\":\"平板电脑\"}]}," +
//                "{\"title\": \"相机\", \"list\":[{\"name\":\"照相机\"}, {\"name\":\"镜头\"}, {\"name\":\"单反镜头\"}, {\"name\":\"数码单反\"}, {\"name\":\"数码相机\"}, {\"name\":\"索尼\"}]}]}";
//        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
//        list2 = rightBean.getDatas();
//        adapter2 = new AllKindRightAdapter(this, list2);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView2.setLayoutManager(manager);
//        recyclerView2.setAdapter(adapter2);
//
//    }
//    private void initData3(){
//        //模拟数据
//        String str = "{\"datas\":[{\"title\": \"推荐\", \"list\":[{\"name\":\"优衣库\"}, {\"name\":\"Zara\"}, " +
//                "{\"name\":\"拉夏贝尔\"}, {\"name\":\"ONLY\"}, {\"name\":\"Vero Moda\"}, {\"name\":\"MeTe\"}]}, {\"title\": \"推荐\", \"list\":[{\"name\":\"一键转卖\"}, {\"name\":\"品牌低价\"},  {\"name\":\"网红同款\"}]}, {\"title\": \"上装\", \"list\":[{\"name\":\"背心\"}, {\"name\":\"女式卫衣\"},  {\"name\":\"女士T恤\"},  {\"name\":\"主板\"}]}" + ",{\"title\": \"特色服装\", \"list\":[{\"name\":\"礼服\"}, {\"name\":\"一体机\"}, {\"name\":\"台式机\"}, {\"name\":\"笔记本电脑\"},{\"name\":\"平板电脑\"}]}," +
//                "{\"title\": \"相机\", \"list\":[{\"name\":\"照相机\"}, {\"name\":\"镜头\"}, {\"name\":\"单反镜头\"}, {\"name\":\"数码单反\"}, {\"name\":\"数码相机\"}, {\"name\":\"索尼\"}]}]}";
//        RightBean rightBean = new Gson().fromJson(str, RightBean.class);
//        list2 = rightBean.getDatas();
//        adapter2 = new AllKindRightAdapter(this, list2);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView2.setLayoutManager(manager);
//        recyclerView2.setAdapter(adapter2);
//    }

    @Override
    public void onSelect(View view, int position) {
        //选中处理
        LeftBean datasBean = list.get(position);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTypeName().equals(datasBean.getTypeName())) {
                list.get(i).setChick(true);
            } else {
                list.get(i).setChick(false);
            }
        }
        initData1(position);
        //刷新列表
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String msg) {
        Message message = new Message();
        message.what = 1;
        message.obj = msg;
        handler.sendMessage(message);
    }

    @Override
    public void onFailure(String msg) {

    }
}