package com.example.erhuo2.dsl.additem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.view.SquareImageView;

import java.util.List;

public class PlusImgsAdapter extends BaseAdapter {
    private Context context;
    private List<Bitmap> list;
    LayoutInflater layoutInflater;
    private SquareImageView mImageView;

    public PlusImgsAdapter(Context context, List<Bitmap> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size()+1;//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.plus_imgs, null);
        mImageView = (SquareImageView) convertView.findViewById(R.id.plus_imgs_item);
        if (position < list.size()) {
            mImageView.setImageBitmap(list.get(position));
        }else if(list.size() < 6){
            mImageView.setImageResource(R.drawable.plusimg);//最后一个显示加号图片
        }
        return convertView;
    }

}