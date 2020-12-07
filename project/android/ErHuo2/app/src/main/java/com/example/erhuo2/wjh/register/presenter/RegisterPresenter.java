package com.example.erhuo2.wjh.register.presenter;

import android.os.Handler;
import android.util.Log;

import com.example.erhuo2.wjh.ConfigUtil;
import com.example.erhuo2.wjh.login.model.LoginListener;
import com.example.erhuo2.wjh.register.model.RegisterModel;
import com.example.erhuo2.wjh.register.view.RegisterView;


public class RegisterPresenter {
    private RegisterView registerView;
    private RegisterModel registerModelClass;
    private Handler mHandler = new Handler();
    public RegisterPresenter(RegisterView registerView){
        this.registerView = registerView;
    }
    public void upload(){
        String userName = registerView.getUserName();
        String userPassword = registerView.getUserPassword();
        registerModelClass = new RegisterModel();
        String s = ConfigUtil.REGISTER +  "?username="+userName+"&password=" +userPassword;
        Log.e("sss", s);
        registerModelClass.register(s, new LoginListener() {
            @Override
            public void onSuccess(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showMessage(msg);
                    }
                });
            }


            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showMessage(msg);
                    }
                });
            }
        });
    }
}
