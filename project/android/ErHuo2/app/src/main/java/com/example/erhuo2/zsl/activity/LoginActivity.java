package com.example.erhuo2.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.widget.TabHost;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.ServePageFragment;
import com.example.erhuo2.wjh.MessageContactActivity;
import com.example.erhuo2.zsl.page.HomePageFragment;
import com.example.erhuo2.zsl.page.MessagePageFragment;
import com.example.erhuo2.zsl.page.MyPageFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFragmentTabHost();
    }
    private void initFragmentTabHost(){
        FragmentTabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),
                android.R.id.tabcontent);
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("首页");
        tabHost.addTab(tab1, HomePageFragment.class, null);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
                .setIndicator("服务");
        tabHost.addTab(tab2, ServePageFragment.class, null);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
                .setIndicator("消息");
        tabHost.addTab(tab3, MessageContactActivity.class, null);
        TabHost.TabSpec tab4 = tabHost.newTabSpec("tab4")
                .setIndicator("我的");
        tabHost.addTab(tab4, MyPageFragment.class, null);
    }

}