package com.example.erhuo2.wjh.login.presenter;

import android.os.Handler;

import com.example.erhuo2.wjh.ConfigUtil;
import com.example.erhuo2.wjh.login.model.LoginModel;
import com.example.erhuo2.wjh.login.model.LoginListener;
import com.example.erhuo2.wjh.login.view.LoginView;

public class LoginPresenter {
    private LoginView loginView;
    private LoginModel loginModelClass;
    private Handler mHandler = new Handler();

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void getData(){
        String userName = loginView.getUserName();
        String userPassword = loginView.getUserPassword();
        loginModelClass = new LoginModel();
        String s = ConfigUtil.LOGIN +"?username="+userName+"&password=" +userPassword;
        loginModelClass.login(s, new LoginListener() {
            @Override
            public void onSuccess(final String data) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.onSuccess(data);
                    }
                });

            }

            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        loginView.showMessage(msg);
                    }
                });
            }

        });
    }
}
