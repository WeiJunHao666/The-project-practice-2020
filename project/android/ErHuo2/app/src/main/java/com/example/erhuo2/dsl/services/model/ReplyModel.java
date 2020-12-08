package com.example.erhuo2.dsl.services.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.erhuo2.dsl.services.usefulimg.ConfigUtil.SERVER_ADDR;

public class ReplyModel {
    private OkHttpClient okHttpClient;
    private static ReplyModel uniqueInstance = new ReplyModel();
    private ReplyModel(){}
    public static ReplyModel getInstance() {
        return uniqueInstance;
    }

    public void updateThumpUp(final int userId, final int comId) {
        class MyThread extends Thread{
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/like/"+userId+"/"+comId)
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i("lww", "请求失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.i("lww", "请求成功");
                    }
                });
            }
        }
        MyThread thread = new MyThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateThumpDown(final int userId, final int comId) {
        class MyThread extends Thread{
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/unlike/"+userId+"/"+comId)
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i("lww", "请求失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.i("lww", "请求成功");
                    }
                });
            }
        }
        MyThread thread = new MyThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
