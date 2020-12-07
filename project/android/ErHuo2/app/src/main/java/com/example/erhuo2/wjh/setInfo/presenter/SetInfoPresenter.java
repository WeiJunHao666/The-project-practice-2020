package com.example.erhuo2.wjh.setInfo.presenter;

import android.os.Handler;

import com.example.erhuo2.wjh.setInfo.model.ModelListener;
import com.example.erhuo2.wjh.setInfo.model.SetInfoModel;
import com.example.erhuo2.wjh.setInfo.view.SetInfoView;


public class SetInfoPresenter {
    private SetInfoView view;
    private SetInfoModel model;
    private Handler mHandler = new Handler();
    public SetInfoPresenter(SetInfoView view){
        this.view = view;
    }
    public void uploadQNY(String s){
        model = new SetInfoModel();
        model.setInfo(s, new ModelListener() {
            @Override
            public void onSuccess(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onSuccess(msg);
                    }
                });
            }

            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onFailure(msg);
                    }
                });
            }
        });
    }
    public void uploadFWQ(String s, String json){
        model = new SetInfoModel();
        model.sendName(s,  json, new ModelListener() {
            @Override
            public void onSuccess(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onSuccess1(msg);
                    }
                });
            }

            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onFailure1(msg);
                    }
                });
            }
        });
    }
}
