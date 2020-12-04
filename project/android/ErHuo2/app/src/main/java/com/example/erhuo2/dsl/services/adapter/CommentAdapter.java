package com.example.erhuo2.dsl.services.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.entities.CommentEntity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentEntity> listComment = new ArrayList<>();
    private int item_layout_list;

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
                    updateThumpUp();
                }else{
                    comment_item_like.setImageResource(R.drawable.ex_thump_up);
                    prizes.setText(Integer.parseInt(prizes.getText().toString())-1+"");
                    listComment.get(position).setLike(false);
                    Log.e("dsl","thump down !!!");
                    //更新点赞数
                    updateThumpUp();
                }
            }
        });

        return convertView;
    }

    private void updateThumpUp() {
    }
}