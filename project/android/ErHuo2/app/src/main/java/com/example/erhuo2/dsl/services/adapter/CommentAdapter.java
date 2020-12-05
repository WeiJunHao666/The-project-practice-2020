package com.example.erhuo2.dsl.services.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.ViewServiceActivity;
import com.example.erhuo2.dsl.services.entities.CommentEntity;
import com.example.erhuo2.dsl.services.entities.CommentInfoToSer;
import com.example.erhuo2.dsl.services.entities.MyEvent;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentEntity> listComment = new ArrayList<>();
    private int item_layout_list;
    private OkHttpClient okHttpClient;


    public CommentAdapter(Context context, List<CommentEntity> listComment, int item_layout_list) {
        this.context = context;
        this.listComment = listComment;
        this.item_layout_list = item_layout_list;
    }

    @Override
    public int getCount() {
        return listComment.size();
    }

    @Override
    public Object getItem(int position) {
        return listComment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 加载列表布局文件
        if(null == convertView){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(item_layout_list,null);
        }

        CircleImageView img = convertView.findViewById(R.id.ico);
        TextView user = convertView.findViewById(R.id.comment_user);
        final ImageView comment_item_like = convertView.findViewById(R.id.comment_item_like);
        final TextView prizes = convertView.findViewById(R.id.comment_prizes);
        TextView data = convertView.findViewById(R.id.comment_data);

        ListView service_reply_list = convertView.findViewById(R.id.service_reply_list);

        img.setImageResource(listComment.get(position).getImg());
        user.setText(listComment.get(position).getName());
        prizes.setText(listComment.get(position).getPrizes());
        data.setText(listComment.get(position).getContent());

        ReplyAdapter ra = new ReplyAdapter(context,listComment.get(position).getList(), R.layout.reply_list);
        service_reply_list.setAdapter(ra);

        service_reply_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                MyEvent e = new MyEvent(position,position1);
                EventBus.getDefault().post(e);
            }
        });

        if(listComment.get(position).isLike()){
            comment_item_like.setImageResource(R.drawable.ed_thump_up);
        }else{
            comment_item_like.setImageResource(R.drawable.ex_thump_up);
        }


        comment_item_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listComment.get(position).isLike()){
                    comment_item_like.setImageResource(R.drawable.ed_thump_up);
                    prizes.setText(Integer.parseInt(prizes.getText().toString())+1+"");
                    listComment.get(position).setLike(true);
                    Log.e("dsl","thump up !!!");
                    //更新点赞数
                    //updateLike(1, listComment.get(position).getComId());
                    //notifyDataSetChanged();
                }else{
                    comment_item_like.setImageResource(R.drawable.ex_thump_up);
                    prizes.setText(Integer.parseInt(prizes.getText().toString())-1+"");
                    listComment.get(position).setLike(false);
                    Log.e("dsl","thump down !!!");
                    //更新点赞数
                    //updateUnLike(1,listComment.get(position).getComId());
                    //notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    private void updateLike(final int userId, final int comId) {
        new Thread(){
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/like/"+userId+"/"+comId)
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
                        Log.i("lww", "请求成功");
                    }
                });
            }
        }.start();

    }

    private void updateUnLike(final int userId, final int comId) {
        new Thread(){
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/unlike/"+userId+"/"+comId)
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
                        Log.i("lww", "请求成功");
                    }
                });
            }
        }.start();
    }
}