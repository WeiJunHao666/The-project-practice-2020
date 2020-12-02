package com.example.erhuo2.wjh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.erhuo2.R;

import java.util.ArrayList;
import java.util.List;

public class MessageContactActivity extends Fragment {
    private View root;
    private ListView messageList;
    private List<MessageData> data = new ArrayList<>();
    private MessageListAdapter messageListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_message_contact, container, false);
        messageList = root.findViewById(R.id.message_listView);
        MessageData data1 = new MessageData(R.drawable.a, "张树林", "你好", "刚刚", R.drawable.g);
        MessageData data2 = new MessageData(R.drawable.b, "张树林", "你好", "刚刚", R.drawable.f);
        MessageData data3 = new MessageData(R.drawable.c, "张树林", "你好", "刚刚", R.drawable.b);
        MessageData data4 = new MessageData(R.drawable.d, "张树林", "你好", "刚刚", R.drawable.a);
        MessageData data5 = new MessageData(R.drawable.e, "张树林", "你好", "刚刚", R.drawable.c);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        messageListAdapter =new MessageListAdapter(getActivity().getApplicationContext(), data, R.layout.item_message_list);
        messageList.setAdapter(messageListAdapter);
        return root;
    }
}