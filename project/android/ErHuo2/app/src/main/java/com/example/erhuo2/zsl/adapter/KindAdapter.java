package com.example.erhuo2.zsl.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.erhuo2.zsl.entities.KindEntities;

import java.util.List;

public class KindAdapter extends BaseAdapter {
    private Context context;
    private List<KindEntities> list;
    private int item_layout_id;
    public KindAdapter(Context context, List<KindEntities> list,int item_layout_id){
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        convertView = layoutInflater.inflate(R.layout.list_item, null);
//        ImageView imgKind = (ImageView)  convertView.findViewById(R.id.kind_img)
//        TextView tvName = (TextView) convertView.findViewById(R.id.kind_name);
//        CityItem city = list.get(position);
//
//        tvCity.setText(city.getCityName());
//        tvCode.setText(city.getCityCode());
        return view;
    }
}
