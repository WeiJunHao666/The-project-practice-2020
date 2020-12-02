package com.example.erhuo2.wjh.allKind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erhuo2.R;

import java.util.ArrayList;
import java.util.List;

public class AllKindLeftAdapter extends RecyclerView.Adapter<AllKindLeftAdapter.ViewHolder> {
    private Context context;
    private List<ThemeMainReq.DatasBean> list;
    private List<Integer> liveList=new ArrayList<>();
    private View inflater;
    private OnSelectorListener mSelectorListener;

    public AllKindLeftAdapter(Context context, List<ThemeMainReq.DatasBean> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_all_kind_left,parent,false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //将数据和控件绑定
        holder.itemView.setId(position);
        holder.textView.setText(list.get(position).getShowName());

        holder.setIsRecyclable(false);
        if (list.get(position).isChick()) {
            holder.itemView.setBackgroundResource(R.drawable.a);
            holder.textView.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }

    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }

    public interface OnSelectorListener {
        void onSelect(View view, int position);
    }

    public void setOnSelectorListener(OnSelectorListener listener) {
        mSelectorListener = listener;
    }

    //内部类，绑定控件
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.kind_left);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    mSelectorListener.onSelect(v, getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
