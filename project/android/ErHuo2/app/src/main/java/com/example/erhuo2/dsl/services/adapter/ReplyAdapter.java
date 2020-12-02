package com.example.erhuo2.dsl.services.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;

import java.util.ArrayList;
import java.util.List;

public class ReplyAdapter extends BaseAdapter {
    private Context context;
    private List<ReplyEntity> listReply = new ArrayList<>();
    private int item_layout_list;

    public ReplyAdapter(Context context, List<ReplyEntity> listReply, int item_layout_list) {
        this.context = context;
        this.listReply = listReply;
        this.item_layout_list = item_layout_list;
    }
    @Override
    public int getCount() {
        return listReply.size();
    }

    @Override
    public Object getItem(int position) {
        return listReply.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 加载列表布局文件
        if(null == convertView){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(item_layout_list,null);
        }

        TextView reply_user = convertView.findViewById(R.id.reply_user);
        final TextView reply_prizes = convertView.findViewById(R.id.reply_prizes);
        final ImageView reply_item_like = convertView.findViewById(R.id.reply_item_like);
        TextView reply_data = convertView.findViewById(R.id.reply_data);

        reply_user.setText(listReply.get(position).getName());
        reply_prizes.setText(listReply.get(position).getPrizes());
        reply_data.setText(listReply.get(position).getContent());

        final boolean[] status = {false};

        reply_item_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!status[0]){
                    reply_item_like.setImageResource(R.drawable.ed_thump_up);
                    reply_prizes.setText(Integer.parseInt(reply_prizes.getText().toString())+1+"");
                    status[0] = true;
                    Log.e("dsl","thump up !!!");
                    //更新点赞数
                    updateThumpUp();
                }else{
                    reply_item_like.setImageResource(R.drawable.ex_thump_up);
                    reply_prizes.setText(Integer.parseInt(reply_prizes.getText().toString())-1+"");
                    status[0] = false;
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
