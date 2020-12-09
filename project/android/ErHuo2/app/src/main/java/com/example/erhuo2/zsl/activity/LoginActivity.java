package com.example.erhuo2.zsl.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.ServePageFragment;
import com.example.erhuo2.wjh.message.MessageContactActivity;
import com.example.erhuo2.zsl.page.HomePageFragment;
import com.example.erhuo2.zsl.page.MyOnePage;
import com.example.erhuo2.zsl.page.MyPageFragment;
import com.example.erhuo2.zsl.page.MyThreePage;
import com.example.erhuo2.zsl.page.MyTwoPage;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class LoginActivity extends AppCompatActivity {
    private BottomBar bottomBar;

    private BottomBarTab nearby;
    private MyPageFragment myPageFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Object ob=null;
                switch (tabId){
                    case R.id.tab_home:
                        // 选择指定 id 的标签
                        nearby = bottomBar.getTabWithId(R.id.tab_my);
//                        nearby.setBadgeCount(5);
                        ob  = new HomePageFragment();
                        break;
                    case R.id.tab_service:
                        // 选择指定 id 的标签
                        nearby = bottomBar.getTabWithId(R.id.tab_my);
                        //nearby.setBadgeCount(5);
                        ob  = new ServePageFragment();
                        break;
                    case R.id.tab_message:
                        // 选择指定 id 的标签
                        nearby = bottomBar.getTabWithId(R.id.tab_my);
                        //nearby.setBadgeCount(5);
                        ob  = new MessageContactActivity();
                        break;
                    case R.id.tab_my:
                        // 选择指定 id 的标签
                        nearby = bottomBar.getTabWithId(R.id.tab_my);
                        //nearby.setBadgeCount(5);
                        ob  = new MyPageFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,(Fragment) ob).commit();
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    // 已经选择了这个标签，又点击一次。即重选。
                    nearby.removeBadge();
                }
            }
        });


    }

}