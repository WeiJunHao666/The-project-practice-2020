package com.example.erhuo2.dsl.services;

import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.example.erhuo2.dsl.services.entities.ComInfoEntity;
import com.example.erhuo2.dsl.services.entities.CommentEntity;
import com.example.erhuo2.dsl.services.entities.CommentInfoToSer;
import com.example.erhuo2.dsl.services.entities.MyEvent;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;
import com.example.erhuo2.dsl.services.view.SquareImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaren.lib.view.LikeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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

import static com.example.erhuo2.dsl.services.usefulimg.ConfigUtil.SERVER_ADDR;

public class ViewServiceActivity extends AppCompatActivity {

    private MyEvent toCom;
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
                    commentList.add(c);
                    ca.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "评论成功！",
                            Toast.LENGTH_SHORT).show();
                    service_discuss_content.setText("");
                    break;
                case 3:
                    Log.e("dsl","33333333");
                    ReplyEntity r = (ReplyEntity) msg.obj;
                    commentList.get(toCom.getPrePosition()).getList().add(r);
                    ca.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "评论成功！",
                            Toast.LENGTH_SHORT).show();
                    service_discuss_content.setText("");
                    break;
            }
        }
    };

    //订阅者
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyEvent event) {
        toCom = event;
        String name="";
        if(toCom.getAftPosition()!=-1){
            name = commentList.get(toCom.getPrePosition()).getList().get(toCom.getAftPosition()).getName();
        }else{
            name = commentList.get(toCom.getPrePosition()).getName();
        }
        service_discuss_content.setHint("张树林 回复 "+name);
        //聚焦并显示软键盘
        service_discuss_content.setFocusable(true);
        service_discuss_content.setFocusableInTouchMode(true);
        service_discuss_content.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) service_discuss_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(service_discuss_content, 0);
        service_discuss_content.setText("");

    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);


        Intent request = getIntent();
        String name = request.getStringExtra("name");
        String img = request.getStringExtra("img");
        String content = request.getStringExtra("content");
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


    }

    private void addComment() {

        //请求评论数据
        //getCommentData();

        ReplyEntity r1 = new ReplyEntity(1,1,"杜世龙","张树林",12+"","llllll",true);
        ReplyEntity r2 = new ReplyEntity(1,1,"韦俊豪","张树林",15+"","llllll",false);

        List<ReplyEntity> l1 = new ArrayList<>();
        l1.add(r1);
        l1.add(r2);
        CommentEntity c1 = new CommentEntity(1,1,"张树林",R.drawable.first,123+"","6666",l1,false);

        List<ReplyEntity> l3 = new ArrayList<>();
        l3.add(r1);
        CommentEntity c2 = new CommentEntity(1,1,"张树林",R.drawable.first,0+"","777",l3,false);

        List<ReplyEntity> l2 = new ArrayList<>();
        CommentEntity c3 = new CommentEntity(1,1,"张树林",R.drawable.first,1+"","6888",l2,true);

        commentList.add(c1);
        commentList.add(c2);
        commentList.add(c3);

        ca = new CommentAdapter(getApplicationContext(),commentList, R.layout.comment_list);
        service_comment_list.setAdapter(ca);


        service_comment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                MyEvent e = new MyEvent(position1,-1);
                EventBus.getDefault().post(e);
            }
        });

    }

    //获取评论信息
    private void getCommentData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String s = SERVER_ADDR+"comment/getCom/1/1";
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
                                    comInfos.get(j).get(i).getComUser() ,comInfos.get(j).get(i).getLastUser(),
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
                    if(service_discuss_content.getText().toString().trim().equals("")){
                        Toast.makeText(getApplicationContext(), "评论内容不能为空！",
                                Toast.LENGTH_SHORT).show();
                    }


                    if(service_discuss_content.getHint().toString().equals("说点什么吧~")){
                        //评论
                        //sendComment();

                        String content = service_discuss_content.getText().toString();
                        List<ReplyEntity> list = new ArrayList<>();
                        CommentEntity s = new CommentEntity(1,1,"张树林",R.drawable.first,"0",content,list,false);
                        Message msg = handler.obtainMessage();
                        msg.what = 2;
                        msg.obj = s;
                        handler.sendMessage(msg);
                        service_discuss_content.setText("");

                    }else{
                        //回复
                        //sendReply();

                        String content = service_discuss_content.getText().toString();
                        if(toCom.getAftPosition()!=-1){
                            ReplyEntity r = new ReplyEntity(1,1,"张树林",
                                    commentList.get(toCom.getPrePosition()).getList().get(toCom.getAftPosition()).getName(),
                                    0+"",content,false);

                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.obj = r;
                            handler.sendMessage(msg);
                        }else{
                            ReplyEntity r = new ReplyEntity(1,1,"张树林",
                                    commentList.get(toCom.getPrePosition()).getName(),
                                    0+"",content,false);

                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.obj = r;
                            handler.sendMessage(msg);
                        }

                        service_discuss_content.setHint("说点什么吧~");
                        service_discuss_content.setText("");
                    }
                    break;
            }
        }
    }

    private void sendReply() {
        new Thread(){
            @Override
            public void run() {
                final String content = service_discuss_content.getText().toString();
                CommentInfoToSer c;
                if(toCom.getAftPosition()!=-1){
                    c = new CommentInfoToSer(1,1,content,
                            commentList.get(toCom.getPrePosition()).getList().get(toCom.getAftPosition()).getComId(),
                            commentList.get(toCom.getPrePosition()).getList().get(toCom.getAftPosition()).getUserId());
                }else{
                    c = new CommentInfoToSer(1,1,content,
                            commentList.get(toCom.getPrePosition()).getComId(),
                            commentList.get(toCom.getPrePosition()).getUserId());
                }
                Gson gson = new Gson();
                String jsonStr = gson.toJson(c);
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        jsonStr);
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/addCom")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(getApplicationContext(), "评论失败，请检查网络重新评论！",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String result[] = response.body().string().split("&");
                        int comId = Integer.parseInt(result[0]);
                        if(result[1].equals("1")){
                            if(toCom.getAftPosition()!=-1){
                                ReplyEntity r = new ReplyEntity(comId,1,"张树林",
                                        commentList.get(toCom.getPrePosition()).getList().get(toCom.getAftPosition()).getName(),
                                        0+"",content,false);

                                Message msg = handler.obtainMessage();
                                msg.what = 3;
                                msg.obj = r;
                                handler.sendMessage(msg);
                            }else{
                                ReplyEntity r = new ReplyEntity(1,1,"张树林",
                                        commentList.get(toCom.getPrePosition()).getName(),
                                        0+"",content,false);

                                Message msg = handler.obtainMessage();
                                msg.what = 3;
                                msg.obj = r;
                                handler.sendMessage(msg);
                            }
                        } else{
                            Toast.makeText(getApplicationContext(), "评论失败，请检查网络重新评论！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }.start();
    }

    private void sendComment() {
        new Thread(){
            @Override
            public void run() {
                final String content = service_discuss_content.getText().toString();
                CommentInfoToSer c = new CommentInfoToSer(1,1,content);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(c);
                //1)
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        jsonStr);
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/addCom")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(getApplicationContext(), "评论失败，请检查网络重新评论！",
                                Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), "评论失败，请检查网络重新评论！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }.start();
    }
}