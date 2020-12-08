package com.example.erhuo2.wjh.searchResult;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.erhuo2.R;
import com.example.erhuo2.SearchPageActivity;
import com.example.erhuo2.dsl.services.ServePageFragment;
import com.example.erhuo2.wjh.message.MessageContactActivity;
import com.example.erhuo2.zsl.page.HomePageFragment;
import com.example.erhuo2.zsl.page.MyPageFragment;

import java.util.ArrayList;


public class SearchResultActivity extends AppCompatActivity {
    private PopupWindow popupWindow;
    private TextView search_choose;
    private ImageView back;
    private Button keyWord;
    private Button go;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    private String []img = {"http://qkl7o9qw8.hb-bkt.clouddn.com/dsl", "http://qkl7o9qw8.hb-bkt.clouddn.com/wjh", "http://qkl7o9qw8.hb-bkt.clouddn.com/wjh",
            "http://qkl7o9qw8.hb-bkt.clouddn.com/dsl", "http://qkl7o9qw8.hb-bkt.clouddn.com/wjh", "http://qkl7o9qw8.hb-bkt.clouddn.com/zsl", "http://qkl7o9qw8.hb-bkt.clouddn.com/wjh",
            "http://qkl7o9qw8.hb-bkt.clouddn.com/dsl", "http://qkl7o9qw8.hb-bkt.clouddn.com/wjh", "http://qkl7o9qw8.hb-bkt.clouddn.com/dsl"};
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        findViews();
        setListener();
        init();
    }

    private void setListener() {
        MyOnClickListener listener = new MyOnClickListener();
        search_choose.setOnClickListener(listener);
        back.setOnClickListener(listener);
        go.setOnClickListener(listener);
    }

    private void findViews() {
        search_choose = findViewById(R.id.search_choose);
        back = findViewById(R.id.search_result_back);
        keyWord = findViewById(R.id.search_result_keyword);
        go = findViewById(R.id.search_result_go);
        recyclerView = findViewById(R.id.recyclerView_search_result);
        Drawable drawable = getResources().getDrawable(R.drawable.search);
        drawable.setBounds(50, 0, 120, 70);// 第一0是距左边距离，第二0是距上边距离，60分别是长宽
        keyWord.setCompoundDrawables(drawable, null, null, null);// 只放左边
    }
    private void init(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        final int spanCount = 2;
        recyclerView.addItemDecoration(new StaggeredDividerItemDecoration(this, 16, spanCount));
        //解决底部滚动到顶部时，顶部item上方偶尔会出现一大片间隔的问题
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });
        for(int i=0; i<9; i++){
            list.add(img[i]);
        }
        adapter = new SearchResultAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_choose:
                    showAnimation();
                    break;
                case R.id.search_result_keyword:
                    Intent intent = new Intent(SearchResultActivity.this, SearchPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.search_result_back:
                    onBackPressed();
                    break;
                case R.id.search_result_go:
                    showPopupMenu(go);
                    break;
            }
        }
    }
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.zsl_search_result_menu, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.go_home:
                        Intent intent = new Intent(SearchResultActivity.this, HomePageFragment.class);
                        startActivity(intent);
                        break;
                    case R.id.go_service:
                        Intent intent1 = new Intent(SearchResultActivity.this, ServePageFragment.class);
                        startActivity(intent1);
                        break;
                    case R.id.go_chat:
                        Intent intent2 = new Intent(SearchResultActivity.this, MessageContactActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.go_mine:
                        Intent intent3 = new Intent(SearchResultActivity.this, MyPageFragment.class);
                        startActivity(intent3);
                        break;

                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }
    private void showAnimation() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow_choose, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        //设置进出动画
        popupWindow.setAnimationStyle(R.style.RightPopupWindowAnimation);
        popupWindow.setWidth(1100);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置背景透明
        addBackground();
        //引入依附的布局
        View parentView = LayoutInflater.from(SearchResultActivity.this).inflate(R.layout.activity_search_results, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.RIGHT, 0, 0);

        //设置监听事件
        TextView popup_album = (TextView) vPopupWindow.findViewById(R.id.popup_album);
        TextView popup_camera = (TextView) vPopupWindow.findViewById(R.id.popup_camera);
        TextView popup_cancel = (TextView) vPopupWindow.findViewById(R.id.popup_cancel);


    }
    //
    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}