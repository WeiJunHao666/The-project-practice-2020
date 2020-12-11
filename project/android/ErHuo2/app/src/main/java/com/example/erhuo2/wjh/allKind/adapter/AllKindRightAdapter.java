package com.example.erhuo2.wjh.allKind.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;
import com.example.erhuo2.wjh.allKind.bean.RightBean;

import java.util.List;


public class AllKindRightAdapter extends RecyclerView.Adapter<AllKindRightAdapter.ViewHolder> {
    private Context context;
    private List<RightBean.DatasBean> list;
    private View inflater;

    public AllKindRightAdapter(Context context, List<RightBean.DatasBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public AllKindRightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_all_kind_right,parent,false);
        AllKindRightAdapter.ViewHolder viewHolder = new AllKindRightAdapter.ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllKindRightAdapter.ViewHolder holder, int position) {
        //将数据和控件绑定
        holder.textView.setText(list.get(position).getTitle());
        List<RightBean.DatasBean.ListBean> list1 = this.list.get(position).getList();
        ChildAdapter childAdapter = new ChildAdapter(context, list1);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(layoutManager);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        holder.recyclerView.setLayoutParams(layoutParams);
        holder.recyclerView.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }
    //内部类，绑定控件
    class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.kind_right);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_child);
        }
    }

}
