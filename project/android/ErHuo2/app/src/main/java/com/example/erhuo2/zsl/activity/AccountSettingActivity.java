package com.example.erhuo2.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.erhuo2.R;

public class AccountSettingActivity extends AppCompatActivity {
    private ImageView back;
    private RelativeLayout changePassword;
    private RelativeLayout changeAccount;
    private RelativeLayout deleteAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        getViews();
        setOnClickListener();
    }
    public void getViews(){
        back = findViewById(R.id.account_back);
        changePassword = findViewById(R.id.account_change_password);
        changeAccount = findViewById(R.id.account_change_account);
        deleteAccount = findViewById(R.id.account_delete_account);
    }
    public void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        back.setOnClickListener(listener);
        changePassword.setOnClickListener(listener);
        deleteAccount.setOnClickListener(listener);
        changeAccount.setOnClickListener(listener);
    }
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.account_back:
                    onBackPressed();
                    break;
                case R.id.account_change_password:
                    Intent intent = new Intent(AccountSettingActivity.this,ChangePasswordActivity.class);
                    startActivity(intent);
                    break;
                case R.id.account_change_account:
                    break;
                case R.id.account_delete_account:
                    break;
            }
        }
    }
}