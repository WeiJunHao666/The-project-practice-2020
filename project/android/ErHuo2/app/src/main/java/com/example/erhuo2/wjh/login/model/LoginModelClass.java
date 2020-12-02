package com.example.erhuo2.wjh.login.model;

public class LoginModelClass implements LoginModel{

    String userName = "aaa";
    String userPassword = "111";
    @Override
    public void login(final String name, final String password, final LoginListener loginListener) {
        new Thread(){
            @Override
            public void run() {
//                    URL url = new URL(s);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    //设置网络请求的方式为POST
//                    conn.setRequestMethod("POST");
//                    //获取网络输出流
//                    OutputStream out = conn.getOutputStream();
//                    //获取待发送的字符串
//                    String str = string;
//                    out.write(str.getBytes());
//                    //必须获取网络输入流，保证客户端和服务端建立连接
//                    InputStream in = conn.getInputStream();
//                    //使用字符流读取
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(in, "utf-8"));
//                    //读取字符信息
//                    String flag = reader.readLine();
//                    Log.ee("flag", flag);
//                    //关闭流
//                    reader.close();
//                    in.close();
//                    out.close();
//                    switch (flag){
//                        case "1":
//                            loginListener.onSuccess("登录成功");
//                            break;
//                        case "2":
//                            loginListener.onFailure("登录失败");
//                            break;
//                    }
                if((userName.equals(name)) && (userPassword.equals(password))){
                    loginListener.onSuccess("登录成功");
                }else{
                    loginListener.onFailure("登录失败");
                }
            }
        }.start();
    }
}
