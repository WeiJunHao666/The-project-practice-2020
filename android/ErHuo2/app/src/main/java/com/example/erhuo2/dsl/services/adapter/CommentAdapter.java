package com.example.erhuo2.dsl.services.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        // 加载列表布局文件
        if(null == convertView){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(item_layout_list,null);
        }

        CircleImageView img = convertView.findViewById(R.id.ico);
        TextView user = convertView.findViewById(R.id.user);
        ImageView comment_item_like = convertView.findViewById(R.id.comment_item_like);
        TextView prizes = convertView.findViewById(R.id.prizes);
        TextView data = convertView.findViewById(R.id.data);

        ListView service_reply_list = convertView.findViewById(R.id.service_reply_list);

        img.setImageResource(listComment.get(position).getImg());
        user.setText(listComment.get(position).getName());
        prizes.setText(listComment.get(position).getPrizes());
        data.setText(listComment.get(position).getContent());

        ReplyAdapter ra = new ReplyAdapter(context,listComment.get(position).getList(),R.layout.reply_list);
        service_reply_list.setAdapter(ra);

        return convertView;
    }
}
