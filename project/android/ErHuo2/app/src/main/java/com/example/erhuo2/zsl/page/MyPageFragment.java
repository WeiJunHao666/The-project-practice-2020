package com.example.erhuo2.zsl.page;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.erhuo2.R;
import com.example.erhuo2.zsl.activity.EditActivity;
import com.example.erhuo2.zsl.activity.LoginActivity;
import com.example.erhuo2.zsl.activity.SettingActivity;
import com.example.erhuo2.zsl.view.MyScrollView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyPageFragment extends Fragment{
    private View contextView;// 总视图
    private ImageView set;
    private Button edit;
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
    private MyTwoPage myTwoPage;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList fragmentList = new ArrayList<Fragment>();
    String[] temp = {"二货","收藏","贴子"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.my_page_fragment, container, false);
        init();
        setClickListener();
        tabLayout = contextView.findViewById(R.id.my_page_tab);
        viewpager = contextView.findViewById(R.id.my_page_pager);
        return contextView;
    }
    private void init() {
        set = (ImageView) contextView.findViewById(R.id.my_page_set);
        edit = (Button) contextView.findViewById(R.id.my_page_edit);
        radioGroup = (RadioGroup) contextView.findViewById(R.id.radio);
        myPageUser = (RelativeLayout) contextView.findViewById(R.id.my_page_user);
    }
    private void setClickListener(){
        MyOnClickListener listener = new MyOnClickListener();
        set.setOnClickListener(listener);
        edit.setOnClickListener(listener);

    }
    public class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.my_page_set:
                    Intent intent = new Intent(getActivity().getApplicationContext(), SettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_page_edit:
                    Intent intent1 = new Intent(getActivity().getApplicationContext(), EditActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // fragment中嵌套fragment, Manager需要用(getChildFragmentManager())
        MPagerAdapter mPagerAdapter = new MPagerAdapter(getChildFragmentManager());
        initFragment();
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(mPagerAdapter);
    }

    private void initFragment() {
        fragmentList.add(new MyOnePage());
        fragmentList.add(new MyTwoPage());
        fragmentList.add(new MyThreePage());
    }


    class MPagerAdapter extends FragmentPagerAdapter {


        public MPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        //返回tablayout的标题文字;
        @Override
        public CharSequence getPageTitle(int position) {
            return temp[position];
        }
    }

}
