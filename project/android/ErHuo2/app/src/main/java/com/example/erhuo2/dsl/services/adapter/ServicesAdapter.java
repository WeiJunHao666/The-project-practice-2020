package com.example.erhuo2.dsl.services.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.ViewServiceActivity;
import com.example.erhuo2.dsl.services.entities.ServiceEntity;
import com.jaren.lib.view.LikeView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServicesAdapter extends BaseAdapter {
    private Context context;
    private List<ServiceEntity> listServices = new ArrayList<>();
    private int item_layout_list;

    public ServicesAdapter(Context context, List<ServiceEntity> listServices, int item_layout_list) {
        this.context = context;
        this.listServices = listServices;
        this.item_layout_list = item_layout_list;
    }
    @Override
    public int getCount() {
        return listServices.size();
    }

    @Override
    public Object getItem(int position) {
        return listServices.get(position);
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

        CircleImageView service_img = convertView.findViewById(R.id.service_img);
        TextView service_name = convertView.findViewById(R.id.service_name);
        final LikeView service_thump_up = convertView.findViewById(R.id.service_thump_up);
        ImageView service_discuss = convertView.findViewById(R.id.service_discuss);
        final TextView num_thump_up = convertView.findViewById(R.id.num_thump_up);
        TextView num_pageviews = convertView.findViewById(R.id.num_pageviews);
        ImageView service_small_img = convertView.findViewById(R.id.service_small_img);
        TextView service_content = convertView.findViewById(R.id.service_content);

        service_img.setImageResource(listServices.get(position).getImg());
        service_name.setText(listServices.get(position).getName());
        final int img = listServices.get(position).getImg();
        final String name = listServices.get(position).getName();
        num_pageviews.setText("16481");
        service_small_img.setImageResource(R.drawable.first);
        service_content.setText("近些年来，越来越多的行业开始和互联网结合，诞生了越来越多的互联网创业公司。互联网创业公司需要面对许多的不确定因素。如果你和你的小伙伴们够幸运，你们的公司可能会在几个星期之内让用户数、商品数、订单量增长几十倍上百倍。一次促销可能会带来平时几十倍的访问流量，一次秒杀活动可能会吸引平时数百倍的访问用户。这对公司自然是极大的好事，说明产品得到认可，公司未来前景美妙。");

        //点赞信息
        num_thump_up.setText(listServices.get(position).getPrizes()+"");
        if(listServices.get(position).getCheck()){
            service_thump_up.setCheckedWithoutAnimator(true);
        }else{
            service_thump_up.setCheckedWithoutAnimator(false);
        }

        class MyListener implements View.OnClickListener {
            private int position;
            public MyListener(int position){
                this.position = position;
            }

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Log.e("ddd",position+"");
                switch (v.getId()) {
                    //点赞
                    case R.id.service_thump_up:
                        //点赞
                        if(!listServices.get(position).getCheck()){
                            service_thump_up.toggle();
                            listServices.get(position).setPrizes(Integer.parseInt(num_thump_up.getText().toString())+1);
                            num_thump_up.setText(listServices.get(position).getPrizes()+"");
                            listServices.get(position).setCheck(true);
                            Log.e("dsl","thump up !!!");
                            //更新点赞数
                            updateThumpUp();
                        }else{
                            //取消点赞
                            service_thump_up.toggle();
                            listServices.get(position).setPrizes(Integer.parseInt(num_thump_up.getText().toString())-1);
                            num_thump_up.setText(listServices.get(position).getPrizes()+"");
                            listServices.get(position).setCheck(false);
                            Log.e("dsl","thump down !!!");
                            //更新点赞数
                            updateThumpUp();
                        }
                        break;
                    //评论跳转
                    case R.id.service_discuss:
                        Intent i = new Intent();
                        i.putExtra("name",name);
                        i.putExtra("img",""+img);
                        i.setClass(context, ViewServiceActivity.class);
                        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                        break;
                }
            }
        }

        //绑定监听器
        MyListener listener = new MyListener(position);
        service_thump_up.setOnClickListener(listener);
        service_discuss.setOnClickListener(listener);

        return convertView;
    }

    private void updateThumpUp() {
    }

}
