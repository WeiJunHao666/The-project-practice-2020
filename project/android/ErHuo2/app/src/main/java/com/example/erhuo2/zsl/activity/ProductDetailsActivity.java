package com.example.erhuo2.zsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erhuo2.R;
import com.example.erhuo2.SearchPageActivity;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView imgBack;
    private ImageView imgSearch;
    private Button btnChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getViews();
        setOnClickListener();
    }
    private void getViews(){
        imgBack = findViewById(R.id.details_back);
        imgSearch = findViewById(R.id.details_search);
        btnChat = findViewById(R.id.details_chat);
    }
    private void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        imgBack.setOnClickListener(listener);
        imgSearch.setOnClickListener(listener);
        btnChat.setOnClickListener(listener);
    }
    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.details_back:
                    onBackPressed();
                    break;
                case R.id.details_search:
                    Intent intent = new Intent(ProductDetailsActivity.this, SearchPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.details_chat:
                    //Intent intent1 = new Intent(ProductDetailsActivity.this,)
            }
        }
    }
}