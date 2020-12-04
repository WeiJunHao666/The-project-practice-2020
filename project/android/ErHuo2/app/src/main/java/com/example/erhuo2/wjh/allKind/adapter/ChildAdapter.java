package com.example.erhuo2.wjh.allKind.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;
import com.example.erhuo2.wjh.SearchResultActivity;
import com.example.erhuo2.wjh.allKind.bean.RightBean;

import java.util.List;


public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private View inflater;
    private Context context;
    private List<RightBean.DatasBean.ListBean> list;

    public ChildAdapter(Context context, List<RightBean.DatasBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_all_kind_child,parent,false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        holder.imageView.setImageResource(R.drawable.a);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_kind_child);
            textView=itemView.findViewById(R.id.tv_kind_child);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setClass(context, SearchResultActivity.class);
                    context.startActivity(i);
                }
            });
        }
    }
}