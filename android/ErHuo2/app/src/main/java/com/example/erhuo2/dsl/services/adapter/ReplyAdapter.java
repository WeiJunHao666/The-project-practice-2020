package com.example.erhuo2.dsl.services.adapter;

import android.content.Context;
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
        TextView reply_prizes = convertView.findViewById(R.id.reply_prizes);
        ImageView reply_item_like = convertView.findViewById(R.id.reply_item_like);
        TextView reply_data = convertView.findViewById(R.id.reply_data);

        reply_user.setText(listReply.get(position).getName());
        reply_prizes.setText(listReply.get(position).getPrizes());
        reply_data.setText(listReply.get(position).getContent());

        return convertView;
    }
}
