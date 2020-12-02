package com.example.erhuo2.wjh.login.presenter;

import android.os.Handler;

import com.example.erhuo2.wjh.login.model.LoginListener;
import com.example.erhuo2.wjh.login.model.LoginModelClass;
import com.example.erhuo2.wjh.login.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter {
    private LoginView loginView;
    private LoginModelClass loginModelClass;
    private Handler mHandler = new Handler();

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void getData(){
        String userName = loginView.getUserName();
        String userPassword = loginView.getUserPassword();
        JSONObject jsonObject = new JSONObject();
        loginModelClass = new LoginModelClass();
        try {
            jsonObject.put("userName", userName);
            jsonObject.put("userPassword", userPassword);
            loginModelClass.login(userName, userPassword, new LoginListener() {
                @Override
                public void onSuccess(final String msg) {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            loginView.showMessage(msg);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
