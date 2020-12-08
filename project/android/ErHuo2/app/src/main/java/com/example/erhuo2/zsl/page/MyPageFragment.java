package com.example.erhuo2.zsl.page;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;
import com.example.erhuo2.zsl.activity.LoginActivity;
import com.example.erhuo2.zsl.view.MyScrollView;

public class MyPageFragment extends Fragment implements MyScrollView.OnScrollListener {
    private View root;
    private ImageView set;
    private ImageView userImg;
    private TextView userName;
    private TextView userPosition;
    private TextView userMessage;
    private MyScrollView myScrollView;
    private RelativeLayout navigation01;
    private RelativeLayout navigation02;
    private RadioGroup radioGroup;
    private RelativeLayout myPageUser;
    private int searchLayoutTop;
    private LoginActivity loginActivity = (LoginActivity) getActivity();
    private boolean hasFocus;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.my_page_fragment, container, false);
        init();
//        radioGroup.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
//            @Override
//            public void onWindowFocusChanged(final boolean hasFocus) {
//                    searchLayoutTop = radioGroup.getBottom();//获取searchLayout的顶部位
//                Log.e("zsl",searchLayoutTop+"");
//            }
//        });
        onWindowFocusChanged(hasFocus);
        return root;
    }
    private void init() {
        navigation01 = (RelativeLayout) root.findViewById(R.id.rl_navigation01);
        navigation02 = (RelativeLayout) root.findViewById(R.id.rl_navigation02);
        myScrollView = (MyScrollView)root.findViewById(R.id.myScrollView);
        radioGroup = (RadioGroup) root.findViewById(R.id.radio);
        myPageUser = (RelativeLayout) root.findViewById(R.id.my_page_user);
        myScrollView.setOnScrollListener(this);

    }
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            searchLayoutTop = radioGroup.getBottom();//获取searchLayout的顶部位置
        }
    }

    //监听滚动Y值变化，通过addView和removeView来实现悬停效果
    @Override
    public void onScroll(int scrollY) {
        if(scrollY >= searchLayoutTop){
            if (radioGroup.getParent()!=navigation01) {
                navigation02.removeView(radioGroup);
                navigation01.addView(radioGroup);
            }
        }else{
            if (radioGroup.getParent()!=navigation02) {
                navigation01.removeView(radioGroup);
                navigation02.addView(radioGroup);
            }
        }
    }
}
