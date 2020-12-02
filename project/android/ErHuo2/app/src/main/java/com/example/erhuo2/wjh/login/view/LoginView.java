package com.example.erhuo2.wjh.login.view;

public interface LoginView {
    /**
     * 获得用户名
     * @return
     */
    String getUserName();

    /**
     * 获得密码
     * @return
     */
    String getUserPassword();

    /**
     * 展示消息
     * @param message
     */
    void showMessage(String message);


}
