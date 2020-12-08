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
        myPageFragment = new MyPageFragment();






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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(myPageFragment instanceof fOnFocusListenable) {
            ((fOnFocusListenable) myPageFragment).onWindowFocusChanged(hasFocus);
        }
    }
    public class MyPagetwo extends Fragment implements View.OnClickListener{
        /**
         * 定义一个布局
         */
        private LayoutInflater inflater;
        //  private View rootView;// 缓存Fragment view
        private Context loginActivity;
        private MyOnePage myOnePage;
        private MyTwoPage myTwoPage;
        private MyThreePage myThreePage;
        /**
         * one、two RadioGroup 控件
         */
        private RadioGroup radioGroup;
        protected RadioButton myOne, myTwo,myThree;

        /**
         * 加载页面
         */
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            loginActivity = getActivity();
            inflater = LayoutInflater.from(getActivity());
//      初始化控件和声明事件
//        rootView = inflater.inflate(R.layout.two, null);
            radioGroup = (RadioGroup) getActivity().findViewById(R.id.radio);
            myOne = (RadioButton) getActivity().findViewById(R.id.my_one);
            myTwo = (RadioButton) getActivity().findViewById(R.id.my_two);
            myThree = (RadioButton) getActivity().findViewById(R.id.my_three);
            //控件颜色
            myOne.setTextColor(getResources().getColor(R.color.tomato));
            myTwo.setTextColor(getResources().getColor(R.color.black));
            myThree.setTextColor(getResources().getColor(R.color.black));
            myOne.setOnClickListener(this);
            myTwo.setOnClickListener(this);
            myThree.setOnClickListener(this);

            setDefaultFragment();
        }
        /**
         * 设置默认的Fragment
         */
        private void setDefaultFragment()
        {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            myOnePage = new MyOnePage();
            transaction.add(R.id.frame, myOnePage).commit();
        }

        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();

            switch (v.getId())
            {
                case R.id.my_one:
                    if (myOnePage == null)
                    {
                        myTwoPage = new MyTwoPage();
                        myThreePage = new MyThreePage();
                    }
                    // 使用当前Fragment的布局替代id_content的控件
                    transaction.replace(R.id.frame, myOnePage);
                    //控件颜色
                    myOne.setTextColor(getResources().getColor(R.color.tomato));
                    myTwo.setTextColor(getResources().getColor(R.color.black));
                    myThree.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.my_two:
                    if (myTwoPage == null)
                    {
                        myTwoPage = new MyTwoPage();
                    }
                    transaction.replace(R.id.frame, myTwoPage);
                    //控件颜色
                    myOne.setTextColor(getResources().getColor(R.color.black));
                    myTwo.setTextColor(getResources().getColor(R.color.tomato));
                    myThree.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.my_three:
                    if (myThreePage == null)
                    {
                        myThreePage = new MyThreePage();
                    }
                    transaction.replace(R.id.frame, myThreePage);
                    //控件颜色
                    myOne.setTextColor(getResources().getColor(R.color.black));
                    myTwo.setTextColor(getResources().getColor(R.color.black));
                    myThree.setTextColor(getResources().getColor(R.color.tomato));
                    break;

            }
            // transaction.addToBackStack();
            // 事务提交
            transaction.commit();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            return inflater.inflate(R.layout.my_page_fragment, null);

        }
    }

}