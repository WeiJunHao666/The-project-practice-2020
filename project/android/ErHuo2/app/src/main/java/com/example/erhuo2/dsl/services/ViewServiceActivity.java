package com.example.erhuo2.dsl.services;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.adapter.CommentAdapter;
import com.example.erhuo2.dsl.services.entities.CommentEntity;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;
import com.example.erhuo2.dsl.services.view.SquareImageView;
import com.jaren.lib.view.LikeView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewServiceActivity extends AppCompatActivity {
    private ImageView service_back;
    private ImageView service_more;
    private CircleImageView view_service_img;
    private TextView view_service_name;
    //private List<Bitmap> imgs = new ArrayList<>();
    private int[] imgs = new int[6];
    private GridLayout gridLayoutPost;

    private TextView shout_content;
    private TextView whole_content;
    private TextView content_spread;
    private TextView content_retract;
    private TextView view_service_pageview;
    private LikeView view_service_thump;
    private TextView view_num_thump;

    private boolean status = false;

    //comment
    private EditText service_discuss_content;
    private Button service_discuss_submit;
    private RelativeLayout theres_no_comment;
    private ListView service_comment_list;
    private List<CommentEntity> commentList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        Intent request = getIntent();
        String name = request.getStringExtra("name");
        String img = request.getStringExtra("img");

        findView();

        setListener();

        view_service_name.setText(name);
        view_service_img.setImageResource(Integer.parseInt(img));
        String str = "近些年来，越来越多的行业开始和互联网结合，诞生了越来越多的互联网创业公司。互联网创业公司需要面对许多的不确定因素。如果你和你的小伙伴们够幸运，你们的公司可能会在几个星期之内让用户数、商品数、订单量增长几十倍上百倍。一次促销可能会带来平时几十倍的访问流量，一次秒杀活动可能会吸引平时数百倍的访问用户。这对公司自然是极大的好事，说明产品得到认可，公司未来前景美妙。";
        shout_content.setText(str);
        whole_content.setText(str);

        //设置更多
        toMore();

        //获取图片
        getimgs();

        //显示图片
        putimgs();

        //获取并显示浏览量
        viewPages();

        //评论
        addComment();

    }

    private void addComment() {

        //请求评论数据

        //请求回复数据

        //判断
        theres_no_comment.setVisibility(View.GONE);

        //绑定Adapter
        ReplyEntity r1 = new ReplyEntity("张树林","12","我是张树林");
        ReplyEntity r2 = new ReplyEntity("树林张","21","我是树林张");
        List<ReplyEntity> rl = new ArrayList<>();
        List<ReplyEntity> rl2 = new ArrayList<>();
        rl.add(r1);
        rl.add(r2);
        CommentEntity comment = new CommentEntity("树林张", R.drawable.first,"123","内容",rl);
        CommentEntity comment2 = new CommentEntity("张shulin", R.drawable.second,"321","内容",rl2);
        commentList.add(comment);
        commentList.add(comment2);

        CommentAdapter ca = new CommentAdapter(this,commentList, R.layout.comment_list);
        service_comment_list.setAdapter(ca);

        //回复
        service_comment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

    }

    private void toMore() {
        final Button btn = new Button(ViewServiceActivity.this);
        btn.setText("举报");
        final PopupWindow popupWindow = new PopupWindow(btn,300,150);//参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"举报成功",Toast.LENGTH_SHORT).show();
            }
        });

        service_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(service_more);
            }
        });
    }

    private void viewPages() {
        view_service_pageview.setText("123456 次浏览");
        view_num_thump.setText("123");
        if(status){
            view_service_thump.setCheckedWithoutAnimator(true);
        }else{
            view_service_thump.setCheckedWithoutAnimator(false);
        }

    }

    private void getimgs() {
        imgs[0] = R.drawable.first;
        imgs[1] = R.drawable.second;
        imgs[2] = R.drawable.first;
        imgs[3] = R.drawable.second;
        imgs[4] = R.drawable.first;
        imgs[5] = R.drawable.second;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void putimgs() {
        gridLayoutPost.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount = 3;//得到列数

        //特判
        if(imgs.length == 4){
            columnCount = 2;
        }
        for (int i = 0, size = imgs.length; i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = GridLayout.spec(i % columnCount, 1.0f);//列数 列宽的比例 weight=1
            ImageView imageView = new SquareImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec = rowSpec;
            layoutParams.columnSpec = columnSpec;

            layoutParams.setMargins(5, 5, 5, 5);

            imageView.setImageResource(imgs[i]);
            gridLayoutPost.addView(imageView, layoutParams);
        }
    }

    private void setListener() {
        MyListener listener = new MyListener();
        service_more.setOnClickListener(listener);
        service_back.setOnClickListener(listener);
        content_spread.setOnClickListener(listener);
        content_retract.setOnClickListener(listener);
        view_service_thump.setOnClickListener(listener);
        service_discuss_submit.setOnClickListener(listener);
    }

    private void findView() {
        service_back = findViewById(R.id.service_back);
        service_more = findViewById(R.id.service_more);
        view_service_img = findViewById(R.id.view_service_img);
        view_service_name = findViewById(R.id.view_service_name);
        gridLayoutPost = findViewById(R.id.gridLayout_post);

        shout_content = findViewById(R.id.shout_content);
        whole_content = findViewById(R.id.whole_content);
        content_spread = findViewById(R.id.content_spread);
        content_retract = findViewById(R.id.content_retract);

        view_service_pageview = findViewById(R.id.view_service_pageview);
        view_service_thump = findViewById(R.id.view_service_thump_up);
        view_num_thump = findViewById(R.id.view_num_thump_up);

        //comment
        service_comment_list = findViewById(R.id.service_comment_list);
        service_discuss_content = findViewById(R.id.service_discuss_content);
        service_discuss_submit = findViewById(R.id.service_discuss_submit);
        theres_no_comment = findViewById(R.id.theres_no_comment);

    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //返回
                case R.id.service_back:
                    onBackPressed();
                    break;
                //展开
                case R.id.content_spread:
                    content_spread.setVisibility(View.GONE);
                    shout_content.setVisibility(View.GONE);

                    content_retract.setVisibility(View.VISIBLE);
                    whole_content.setVisibility(View.VISIBLE);
                    break;
                //收起
                case R.id.content_retract:
                    content_spread.setVisibility(View.VISIBLE);
                    shout_content.setVisibility(View.VISIBLE);

                    content_retract.setVisibility(View.GONE);
                    whole_content.setVisibility(View.GONE);
                    break;
                //点赞
                case R.id.view_service_thump_up:
                    if(!status){
                        view_service_thump.toggle();
                        view_num_thump.setText(Integer.parseInt(view_num_thump.getText().toString())+1+"");
                        status = true;
                    }else{
                        view_service_thump.toggle();
                        view_num_thump.setText(Integer.parseInt(view_num_thump.getText().toString())-1+"");
                        status = false;
                    }
                    break;
                //提交评论
                case R.id.service_discuss_submit:

                    break;
            }
        }
    }
}