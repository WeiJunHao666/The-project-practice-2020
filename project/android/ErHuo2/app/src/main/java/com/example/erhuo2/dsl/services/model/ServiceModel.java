package com.example.erhuo2.dsl.services.model;

import android.util.Log;

import com.example.erhuo2.dsl.services.entities.PostEntity;
import com.example.erhuo2.dsl.services.entities.ServiceEntity;
import com.example.erhuo2.dsl.services.entities.ServiceToSer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.erhuo2.dsl.services.usefulimg.ConfigUtil.SERVER_ADDR;

public class ServiceModel {
    private List<ServiceEntity> serviceList = new ArrayList<>();
    private OkHttpClient okHttpClient;
    private static ServiceModel uniqueInstance = new ServiceModel();
    public ServiceModel(){}
    public static ServiceModel getInstance() {
        return uniqueInstance;
    }

    //请求分页数据
    public void getServiceDate(final int pageNum, final GetPostListener listener){
        class MyThread extends Thread {
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"post/all/"+pageNum+"/"+1)
                        .post(requestBody)
                        .build();


                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);
                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("dsl","请求失败");
                        listener.onFailed("请求失败");
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String jsonStr = response.body().string();
                        Gson gson = new Gson();
                        List<PostEntity> postlist = new ArrayList<>();
                        postlist = gson.fromJson(jsonStr,new TypeToken<List<PostEntity>>(){}.getType());
                        Log.e("dsl","请求成功");
                        serviceList.clear();
                        for(int i = 0; i < postlist.size(); i++){
                            ServiceEntity s = new ServiceEntity(postlist.get(i).getPostId(),
                                    postlist.get(i).getUserId(),postlist.get(i).getUserImg(),
                                    postlist.get(i).getUserName(),postlist.get(i).getPostMes(),
                                    postlist.get(i).getPostDate(),postlist.get(i).getLike(),
                                    postlist.get(i).getLikeNum(),postlist.get(i).getViewNum(),
                                    (ArrayList<String>)postlist.get(i).getImgs());
                            serviceList.add(s);
                        }
                        listener.onSuccess(serviceList);
                    }
                });
            }
        }

        MyThread thread = new MyThread();
        try {
            thread.start();
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //添加帖子
    public void addPost(ServiceToSer s){
        class MyThread extends Thread{
            private ServiceToSer s;
            public MyThread(ServiceToSer s){
                this.s = s;
            }
            @Override
            public void run() {

                Gson gson = new Gson();
                String jsonStr = gson.toJson(s);

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        jsonStr);
                Log.e("dsl","数据发送");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"post/addPost")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("dsl","添加帖子失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.e("dsl","添加帖子成功");
                    }
                });
            }
        }
        MyThread thread = new MyThread(s);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //点赞  取消点赞
    public void updateThumpUp(final int userId, final int comId) {
        class MyThread extends Thread{
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+""+userId+"/"+comId)
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
                        .url(SERVER_ADDR+""+userId+"/"+comId)
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

    //增加了浏览量
    private void addViewPage(){

    }
}
