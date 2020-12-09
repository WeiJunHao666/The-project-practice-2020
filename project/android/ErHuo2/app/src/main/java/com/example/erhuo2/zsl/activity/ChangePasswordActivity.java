package com.example.erhuo2.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.erhuo2.R;
import com.example.erhuo2.wjh.login.view.MainActivity;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView back;
    private EditText newPassword;
    private EditText affirmPassword;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getViews();
        setOnClickListener();
    }
    private void getViews(){
        back = findViewById(R.id.change_password_back);
        newPassword = findViewById(R.id.new_password);
        affirmPassword = findViewById(R.id.affirm_password);
        submit = findViewById(R.id.change_password_submit);
    }
    private void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        back.setOnClickListener(listener);
        submit.setOnClickListener(listener);
    }
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.change_password_back:
                    onBackPressed();
                    break;
                case R.id.change_password_submit:
                    String newPassword1 = newPassword.getText().toString();
                    String affirmPassword1 = affirmPassword.getText().toString();
                    if(newPassword1.isEmpty()){
                        Toast.makeText(getApplicationContext(), "新密码为空", Toast.LENGTH_SHORT).show();
                    }else if(affirmPassword1.isEmpty()){
                        Toast.makeText(getApplicationContext(), "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    }else if(!newPassword1.equals(affirmPassword1)){
                        Toast.makeText(getApplicationContext(), "两次输入不相符", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
            }
        }
    }
}