package com.example.erhuo2.wjh.setInfo.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
                    URL url = null;
                    try {
                        url = new URL(s);
                        Log.e("kk", s);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //设置网络请求的方式为POST
                        conn.setRequestMethod("POST");
                        //获取网络输出流
                        OutputStream out = conn.getOutputStream();
                        //必须获取网络输入流，保证客户端和服务端建立连接
                        InputStream in = conn.getInputStream();
                        //使用字符流读取
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(in, "utf-8"));
                        //读取字符信息
                        String flag = reader.readLine();
                        //关闭流
                        reader.close();
                        in.close();
                        out.close();
                        if(!flag.equals("") && flag!=null){
                            listener.onSuccess(flag);
                        }else{
                            listener.onFailure("上传失败");
                        }
                        Log.e("线程1","thread1");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
