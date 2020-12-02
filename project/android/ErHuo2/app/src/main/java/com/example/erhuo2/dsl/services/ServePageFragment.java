package com.example.erhuo2.dsl.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.adapter.ServicesAdapter;
import com.example.erhuo2.dsl.services.entities.ServiceEntity;

import java.util.ArrayList;
import java.util.List;

public class ServePageFragment extends Fragment {
    private View root;
    private ImageView services_write;
    private List<ServiceEntity> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.serve_page_fragment, container, false);

        services_write = root.findViewById(R.id.services_write);

        MyListener listener = new MyListener();
        services_write.setOnClickListener(listener);

        //获取数据
        getData();

        //绑定Adapter
        ListView lv_words = root.findViewById(R.id.show_all_services);
        ServicesAdapter adapter = new ServicesAdapter(getActivity().getApplicationContext(),
                list,R.layout.service_list);
        lv_words.setAdapter(adapter);

        //查看详情跳转
        lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("name",list.get(position).getName());
                i.putExtra("img",""+list.get(position).getImg());
                //i.putExtra("content", list.get(position).getContent());
                i.setClass(getActivity().getApplicationContext(), ViewServiceActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    private void getData() {
        ServiceEntity s1 = new ServiceEntity(R.drawable.first,"张树林","123");
        ServiceEntity s2 = new ServiceEntity(R.drawable.second,"树林张","123");
        list.add(s1);
        list.add(s2);
        list.add(s1);
        list.add(s2);
        list.add(s1);
        list.add(s2);
    }

    private void findView() {


    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.services_write:

                    break;
            }
        }
    }
}