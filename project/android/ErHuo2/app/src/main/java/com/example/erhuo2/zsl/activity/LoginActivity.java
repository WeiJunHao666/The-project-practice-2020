package com.example.erhuo2.zsl.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.erhuo2.FragmentAdapter;
import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.ServePageFragment;
import com.example.erhuo2.wjh.message.MessageContactActivity;
import com.example.erhuo2.zsl.page.HomePageFragment;
import com.example.erhuo2.zsl.page.MyPageFragment;
import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{
    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        viewPager = findViewById(R.id.view_pager);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new ServePageFragment());
        fragments.add(new MessageContactActivity());
        fragments.add(new MyPageFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);

        MultiChoicesCircleButton.Item item1 = new MultiChoicesCircleButton.Item("Like", getResources().getDrawable(R.drawable.inhome), 30);

        MultiChoicesCircleButton.Item item2 = new MultiChoicesCircleButton.Item("Message", getResources().getDrawable(R.drawable.inquan), 90);

        MultiChoicesCircleButton.Item item3 = new MultiChoicesCircleButton.Item("Tag", getResources().getDrawable(R.drawable.inchat), 150);

        List<MultiChoicesCircleButton.Item> buttonItems = new ArrayList<>();
        buttonItems.add(item1);
        buttonItems.add(item2);
        buttonItems.add(item3);

        MultiChoicesCircleButton multiChoicesCircleButton = (MultiChoicesCircleButton) findViewById(R.id.multiChoicesCircleButton);
        multiChoicesCircleButton.setButtonItems(buttonItems);

        multiChoicesCircleButton.setOnSelectedItemListener(new MultiChoicesCircleButton.OnSelectedItemListener() {
            @Override
            public void onSelected(MultiChoicesCircleButton.Item item, int index) {
                // Do something
            }
        });

        multiChoicesCircleButton.setOnHoverItemListener(new MultiChoicesCircleButton.OnHoverItemListener(){
            @Override
            public void onHovered(MultiChoicesCircleButton.Item item, int index) {
                // Do something
            }
        });


        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .addItem(new BottomNavigationItem(R.drawable.inhome, "主页").
                        setInactiveIconResource(R.drawable.home))
                .addItem(new BottomNavigationItem(R.drawable.inquan, "圈子")
                        .setInactiveIconResource(R.drawable.quanzi))
                .addItem(new BottomNavigationItem(R.drawable.inchat, "聊天")
                        .setInactiveIconResource(R.drawable.chat))
                .addItem(new BottomNavigationItem(R.drawable.inmine, "我的")
                        .setInactiveIconResource(R.drawable.mine))
                .setFirstSelectedPosition(0)
                .setActiveColor("#111111") //选中颜色
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        // ViewPager 滑动事件监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //将滑动到的页面对应的 menu 设置为选中状态
                bottomNavigationBar.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}