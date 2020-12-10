package com.example.erhuo2.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.erhuo2.R;

public class SettingActivity extends AppCompatActivity {
    private ImageView back;
    private RelativeLayout account;
    private RelativeLayout privacy;
    private RelativeLayout help;
    private RelativeLayout service;
    private RelativeLayout us;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getViews();
        setOnClickListener();
    }
    private void getViews(){
        back = findViewById(R.id.setting_back);
        account = findViewById(R.id.setting_account);
        privacy = findViewById(R.id.setting_privacy);
        help = findViewById(R.id.setting_help);
        service = findViewById(R.id.setting_service);
        us = findViewById(R.id.setting_us);
        logout = findViewById(R.id.setting_logout);
    }
    private void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        back.setOnClickListener(listener);
        account.setOnClickListener(listener);
        privacy.setOnClickListener(listener);
        help.setOnClickListener(listener);
        service.setOnClickListener(listener);
        us.setOnClickListener(listener);
        logout.setOnClickListener(listener);
    }
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.setting_back:
                    onBackPressed();
                    break;
                case R.id.setting_account:
                    Intent intent = new Intent(SettingActivity.this,AccountSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.setting_privacy:
                    Intent intent1 = new Intent(SettingActivity.this,PrivacyActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.setting_help:
                    Intent intent2 = new Intent(SettingActivity.this,HelpActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.setting_service:
                    Intent intent3 = new Intent(SettingActivity.this,CustomerSeviceActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.setting_us:
                    Intent intent4 = new Intent(SettingActivity.this,AboutUsActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.setting_logout:
                    break;
            }
        }
    }
}