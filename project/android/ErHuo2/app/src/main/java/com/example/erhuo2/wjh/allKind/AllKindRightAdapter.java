package com.example.erhuo2.wjh.allKind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;

import java.util.List;

public class AllKindRightAdapter extends RecyclerView.Adapter<AllKindRightAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    private View inflater;

    public AllKindRightAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_all_kind_right,parent,false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //将数据和控件绑定
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }
    //内部类，绑定控件
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.kind_right);
        }
    }

}
