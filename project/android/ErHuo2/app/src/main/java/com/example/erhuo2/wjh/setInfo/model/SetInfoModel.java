package com.example.erhuo2.wjh.setInfo.model;

import android.util.Log;

import com.example.erhuo2.uploadUtils.GetToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetInfoModel implements Model{
    private static Object myLock1 = new Object();
    Thread thread1;
    Thread thread2;
    @Override
    public void setInfo(final String s, final ModelListener listener) {
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1){
                    String flag = GetToken.getToken(s);
                    if(!flag.equals("") && flag!=null){
                        listener.onSuccess(flag);
                    }else{
                        listener.onFailure("上传失败");
                    }
                    Log.e("线程1","thread1");
                }
            }

        });
        thread1.start();
    }

    @Override
    public void sendName(final String s, final String json, final ModelListener listener) {
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1){
                    try {
                        Log.e("json", json);
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json;charset=utf-8"));
                        Request request = new Request.Builder()
                                .post(requestBody)
                                .url(s)
                                .build();
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                listener.onFailure("上传失败");
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                listener.onSuccess("上传成功");
                                System.out.println(response.body().string());
                            }
                        });
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        });
        thread2.start();
    }
}
