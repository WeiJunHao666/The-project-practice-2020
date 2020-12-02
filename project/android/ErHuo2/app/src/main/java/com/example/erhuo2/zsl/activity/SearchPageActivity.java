package com.example.erhuo2.zsl.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erhuo2.R;
import com.example.erhuo2.zsl.util.SearchHistoryUtils;
import com.example.erhuo2.zsl.view.RefreshableView1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SearchPageActivity extends AppCompatActivity {
    private RefreshableView1 refreshableView1;
    private EditText keyWord;
    private Button search;
    private ImageView back;
    private SimpleDateFormat mFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        gitViews();
        mFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );

        keyWord.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) keyWord.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchPageActivity. this .getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //搜索具体逻辑
                    //搜索请求转交给函数去处理：
                    //search(String.valueOf(mEditTextSearch.getText()));
                    Toast.makeText(SearchPageActivity. this , "按下了搜索按钮" ,Toast.LENGTH_SHORT).show();
                    return true ;
                }
                return false ;
            }
        });
//        refreshableView1 = (RefreshableView1) findViewById(R.id.refreshable_view);
//        refreshableView1.setOnRefreshListener(new RefreshableView1.PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException ee) {
//                    ee.printStackTrace();
//                }
//                refreshableView1.finishRefreshing();
//            }
//        }, 0);
    }
    public void gitViews(){
        keyWord = findViewById(R.id.search_keyword);
        search = findViewById(R.id.search_search);
        back = findViewById(R.id.search_back);
    }
    public void setOnClickListener(){
        MyOnClickListener listener = new MyOnClickListener();

    }
    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.search_back:
                case R.id.search_search:
                case R.id.list_view:

            }
        }
    }
//    private void saveSearchHistory(String keyWords) {
//        //保存之前要先查询sp中是否有该value的记录，有则删除.这样保证搜索历史记录不会有重复条目
//        Map<String, String> historys = (Map<String, String>) SearchHistoryUtils.getAll(getActivity());
//        for (Map.Entry<String, String> entry : historys.entrySet()) {
//            if (keyWords.equals(entry.getValue())){
//                SearchHistoryUtils.remove(getActivity(),entry.getKey());
//            }
//        }
//        SearchHistoryUtils.put(getActivity(), "" + mFormat.format( new Date()), keyWords);
//    }
}