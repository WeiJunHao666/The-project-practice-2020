package com.example.erhuo2.wjh.register.model;

import com.example.erhuo2.wjh.login.model.ModelListener;

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

public class RegisterModel implements Model {
    @Override
    public void register(final String s, final ModelListener registerListener) {
        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(s);
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
                    if(flag.equals("error")){
                        registerListener.onFailure("注册失败");
                    }else if(flag.equals("ok")){
                        registerListener.onSuccess("注册成功");
                    }
                } catch (MalformedURLException | ProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}