package com.example.erhuo2.dsl.services.model;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.erhuo2.R;
import com.example.erhuo2.dsl.services.entities.ComInfoEntity;
import com.example.erhuo2.dsl.services.entities.CommentEntity;
import com.example.erhuo2.dsl.services.entities.CommentInfoToSer;
import com.example.erhuo2.dsl.services.entities.ReplyEntity;
import com.example.erhuo2.dsl.services.entities.ServiceEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private OkHttpClient okHttpClient;
    private static ServiceModel uniqueInstance = new ServiceModel();
    public ServiceModel(){}
    public static ServiceModel getInstance() {
        return uniqueInstance;
    }

    //请求分页数据
    public List<ServiceEntity> getServiceDate(int pageNum){
        List<ServiceEntity> list = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/addCom")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    }
                });
            }
        }.start();

        return list;
    }

    //添加帖子
    public void addPost(){
        new Thread(){
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse("text/plain;charset=utf-8"),
                        "");
                //2) 创建请求对象
                final Request request = new Request.Builder()
                        .url(SERVER_ADDR+"comment/addCom")
                        .post(requestBody)
                        .build();
                //3. 创建CALL对象
                okHttpClient = new OkHttpClient();
                Call call = okHttpClient.newCall(request);

                //4. 提交请求并获取响应
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    }
                });
            }
        }.start();
    }


    //点赞  取消点赞
    public void updateThumpUp(final int userId, final int comId) {
        new Thread(){
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
        }.start();
    }

    public void updateThumpDown(final int userId, final int comId) {
        new Thread(){
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
        }.start();
    }


}
