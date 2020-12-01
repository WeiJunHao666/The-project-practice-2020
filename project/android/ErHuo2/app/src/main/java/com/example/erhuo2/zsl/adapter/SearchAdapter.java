package com.example.erhuo2.zsl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erhuo2.R;

import java.util.List;
import java.util.Map;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> dataSource;
    private int item_layout_id;

    public SearchAdapter(Context context,List<Map<String,Object>> dataSource,int item_layout_id){
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            //加载列表项布局文件
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(item_layout_id,null);
        }
        //接下来先获取列表项中的控件对象
        //TextView search_name = convertView.findViewById(R.id.search_name);
        TextView search_position = convertView.findViewById(R.id.search_position);
        TextView search_describe = convertView.findViewById(R.id.search_describe);
        TextView search_price = convertView.findViewById(R.id.search_price);
        ImageView search_img = convertView.findViewById(R.id.search_img);
        TextView search_seller = convertView.findViewById(R.id.search_seller);
        //给数据项填充数据
        final Map<String,Object> mItemData = dataSource.get(position);
        //search_name.setText((CharSequence) mItemData.get("name"));
        search_describe.setText((CharSequence) mItemData.get("describe"));
        search_price.setText((CharSequence) mItemData.get("price"));
        search_img.setImageBitmap((Bitmap) mItemData.get("img"));
        search_position.setText((CharSequence) mItemData.get("position"));
        search_seller.setText((CharSequence) mItemData.get("seller"));
        //返回列表
        return convertView;
    }
}
