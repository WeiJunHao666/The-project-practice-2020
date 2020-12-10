package com.example.erhuo2.wjh.allKind.presenter;


import com.example.erhuo2.wjh.allKind.model.KindListener;
import com.example.erhuo2.wjh.allKind.model.KindModel;
import com.example.erhuo2.wjh.allKind.view.KindView;

public class KindPresenter {
    private KindView kindView;
    private KindModel kindModel;
    public  KindPresenter(KindView kindView){
        this.kindView = kindView;
    }
    public void showRight(String url){
        kindModel = new KindModel();
        kindModel.showRight(url, new KindListener() {
            @Override
            public void onSuccess(String msg) {
                kindView.onSuccess(msg);
            }

            @Override
            public void onFailure(String msg) {
                kindView.onFailure(msg);
            }
        });
    }
}
