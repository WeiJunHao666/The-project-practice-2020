package com.example.erhuo2.dsl.services;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.erhuo2.dsl.services.entities.ComInfoEntity;
import com.example.erhuo2.dsl.services.entities.CommentEntity;
import com.example.erhuo2.dsl.services.entities.CommentInfoToSer;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;
import com.example.erhuo2.dsl.services.view.SquareImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaren.lib.view.LikeView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ViewServiceActivity extends AppCompatActivity {

    private ImageView service_back;
    private ImageView service_more;
    private CircleImageView view_service_img;
    private TextView view_service_name;
    //private List<Bitmap> imgs = new ArrayList<>();
    private ArrayList<Integer> imgs = new ArrayList<>();
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
    private List<List<ComInfoEntity>> comInfos = new ArrayList<>();
    private CommentAdapter ca;

    private OkHttpClient okHttpClient;


    private Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(commentList.size() > 0){
                        theres_no_comment.setVisibility(View.GONE);
                        ca = new CommentAdapter(getApplicationContext(),commentList, R.layout.comment_list);
                        service_comment_list.setAdapter(ca);
                    }
                    break;
                case 2:
                    Log.e("dsl","111111");
                    CommentEntity c = (CommentEntity) msg.obj;
                    commentList.add(0,c);
                    ca.notifyDataSetChanged();
                    break;
            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        Intent request = getIntent();
        String name = request.getStringExtra("name");
        String img = request.getStringExtra("img");
        String content = request.getStringExtra("content");
        String check = request.getStringExtra("check");
        imgs = request.getIntegerArrayListExtra("imgs");

        findView();

        setListener();

        view_service_name.setText(name);
        view_service_img.setImageResource(Integer.parseInt(img));
        String str = content;
        shout_content.setText(str);
        whole_content.setText(str);

        //设置更多
        toMore();

        //显示图片
        putimgs();

        //获取并显示浏览量
        viewPages();

        //评论
        addComment();

//        service_reply_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                service_discuss_content.requestFocus();
//                service_discuss_content.setHint("回复用户");
//                Log.e("dsl",position+"");
//            }
//        });

    }

    private void addComment() {

        //请求评论数据
        getCommentData();
    }

    //获取评论信息
    private void getCommentData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String s = "http://192.168.43.244:8081/erhuoy/comment/getCom/1/1";
                    URL url = new URL(s);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    //读取字符信息
                    String str = reader.readLine();
                    //关闭流
                    reader.close();
                    in.close();

                    Gson gson = new Gson();
                    comInfos = gson.fromJson(str,new TypeToken<List<List<ComInfoEntity>>>(){}.getType());
                    for(int j = 0; j < comInfos.size(); j++) {

                        //回复列表
                        List<ReplyEntity> rl = new ArrayList<>();
                        for (int i = 1; i < comInfos.get(j).size(); i++) {
                            ReplyEntity re = new ReplyEntity(comInfos.get(j).get(i).getComId(),
                                    comInfos.get(j).get(i).getUserId(),
                                    comInfos.get(j).get(i).getComUser() + " 回复 " + comInfos.get(j).get(i).getLastUser(),
                                    comInfos.get(j).get(i).getLikeNum() + "",
                                    comInfos.get(j).get(i).getMessage(),
                                    comInfos.get(j).get(i).isLike());
                            rl.add(re);
                        }

                        CommentEntity com = new CommentEntity(comInfos.get(j).get(0).getComId(),
                                comInfos.get(j).get(0).getUserId(),
                                comInfos.get(j).get(0).getComUser(),
                                R.drawable.first,
                                comInfos.get(j).get(0).getLikeNum() + "",
                                comInfos.get(j).get(0).getMessage(),
                                rl,
                                comInfos.get(j).get(0).isLike());
                        //评论列表
                        commentList.add(com);
                    }

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void putimgs() {
        gridLayoutPost.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount = 3;//得到列数

        //特判
        if(imgs.size() == 4){
            columnCount = 2;
        }
        for (int i = 0, size = imgs.size(); i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = GridLayout.spec(i % columnCount, 1.0f);//列数 列宽的比例 weight=1
            ImageView imageView = new SquareImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec = rowSpec;
            layoutParams.columnSpec = columnSpec;

            layoutParams.setMargins(5, 5, 5, 5);

            imageView.setImageResource(imgs.get(i));
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

    private class MyListener implements View.OnClickListener {
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

                    //评论
                    sendComment();

                    //回复
                    break;
            }
        }
    }

    private void sendComment() {
        new Thread(){
            @Override
            public void run() {
                final String content = service_discuss_content.getText().toString();
                CommentInfoToSer c = new CommentInfoToSer(1,1,content);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(c);
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        jsonStr);
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url("http://192.168.43.244:8081/erhuoy/comment/addCom")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i("lww", "请求失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String result[] = response.body().string().split("&");
                        int comId = Integer.parseInt(result[0]);
                        if(result[1].equals("1")){
                            List<ReplyEntity> list = new ArrayList<>();
                            CommentEntity c = new CommentEntity(comId,1,"张树林",R.drawable.first,"0",content,list,false);
                            Message msg = handler.obtainMessage();
                            msg.what = 2;
                            msg.obj = c;
                            handler.sendMessage(msg);
                        } else{
                            Log.e("dsl","发布失败");
                        }
                    }
                });
            }
        }.start();
    }
}