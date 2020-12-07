package com.example.erhuo2.wjh.searchResult;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.erhuo2.R;


public class SearchResultActivity extends AppCompatActivity {
    private PopupWindow popupWindow;
    private TextView search_choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        findViews();
        setListener();
    }

    private void setListener() {
        MyOnClickListener listener = new MyOnClickListener();
        search_choose.setOnClickListener(listener);
    }

    private void findViews() {
        search_choose = findViewById(R.id.search_choose);
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_choose:
                    showAnimation();
                    break;
            }
        }
    }

    private void showAnimation() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow_choose, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        //设置进出动画
        popupWindow.setAnimationStyle(R.style.RightPopupWindowAnimation);
        popupWindow.setWidth(1100);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置背景透明
        addBackground();
        //引入依附的布局
        View parentView = LayoutInflater.from(SearchResultActivity.this).inflate(R.layout.activity_search_results, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.RIGHT, 0, 0);

        //设置监听事件
        TextView popup_album = (TextView) vPopupWindow.findViewById(R.id.popup_album);
        TextView popup_camera = (TextView) vPopupWindow.findViewById(R.id.popup_camera);
        TextView popup_cancel = (TextView) vPopupWindow.findViewById(R.id.popup_cancel);


    }
    //
    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}