package com.example.erhuo2.wjh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erhuo2.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageListAdapter extends BaseAdapter {
    private Context myContext;
    private List<MessageData> data = new ArrayList<MessageData>();
    private int itemLayoutRes;

    public MessageListAdapter(Context context, List<MessageData> data, int itemLayoutRes){
        this.myContext = context;
        this.data = data;
        this.itemLayoutRes = itemLayoutRes;
    }
    @Override
    public int getCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(data != null){
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(itemLayoutRes, null);

        CircleImageView message_head = convertView.findViewById(R.id.iv_message_head);
        TextView message_name = convertView.findViewById(R.id.message_name);
        TextView message_info = convertView.findViewById(R.id.message_info);
        TextView message_time = convertView.findViewById(R.id.message_time);
        ImageView message_commodity = convertView.findViewById(R.id.message_commodity);

        message_head.setImageResource(data.get(position).getHeadImage());
        message_name.setText(data.get(position).getName());
        message_info.setText(data.get(position).getInfo());
        message_time.setText(data.get(position).getTime());
        message_commodity.setImageResource(data.get(position).getCommodityImage());

        return convertView;

    }
}
