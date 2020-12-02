package com.example.erhuo2.wjh.register.presenter;

import com.example.erhuo2.wjh.register.model.RegisterModelClass;
import com.example.erhuo2.wjh.register.view.RegisterView;

public class RegisterPresenter {
    private RegisterView registerView;
    private RegisterModelClass registerModelClass;
    public RegisterPresenter(RegisterView registerView){
        this.registerView = registerView;
    }
    public void upload(){
        String userName = registerView.getUserName();
        String userPassword = registerView.getUserPassword();

    }
}
