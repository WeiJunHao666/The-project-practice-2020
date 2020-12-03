package com.example.erhuo2.zsl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erhuo2.R;
import com.example.erhuo2.zsl.entities.ProductEntity;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<ProductEntity> dataSource;
    private int item_layout_id;

    public ProductAdapter(Context context, List<ProductEntity> dataSource, int item_layout_id){
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
        //TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_position = convertView.findViewById(R.id.txt_position1);
        TextView txt_describe = convertView.findViewById(R.id.txt_describe);
        TextView txt_price = convertView.findViewById(R.id.txt_price);
        TextView txt_seller = convertView.findViewById(R.id.txt_seller);
        ImageView img_img = convertView.findViewById(R.id.img_img);
        //给数据项填充数据
        img_img.setImageResource(dataSource.get(position).getImg());
        txt_describe.setText(dataSource.get(position).getDescribe());
        txt_price.setText(dataSource.get(position).getPrice());
        txt_seller.setText(dataSource.get(position).getSeller());
        txt_position.setText(dataSource.get(position).getPosition());
//        final List<ProductEntity> mItemData = dataSource.get(position);
//        //txt_name.setText((CharSequence) mItemData.get("name"));
//        txt_describe.setText((CharSequence) mItemData.get("describe"));
//        txt_position.setText((CharSequence) mItemData.get("position"));
//        txt_price.setText((CharSequence) mItemData.get("price"));
//        img_img.setImageBitmap((Bitmap) mItemData.get("img"));
        //返回列表
        return convertView;
    }
}
