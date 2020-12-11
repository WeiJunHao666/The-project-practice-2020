package com.example.erhuo2.wjh.allKind.presenter;


import com.example.erhuo2.wjh.allKind.bean.RightBean;
import com.example.erhuo2.wjh.allKind.model.KindModel;
import com.example.erhuo2.wjh.allKind.model.KindListener;
import com.example.erhuo2.wjh.allKind.view.KindView;

import java.util.List;

public class KindPresenter {
    private KindView kindView;
    private KindModel kindModel;
    public  KindPresenter(KindView kindView){
        this.kindView = kindView;
    }
    public void showRight(int id){
        kindModel = new KindModel();
        kindModel.showRight(id, new KindListener() {
            @Override
            public void onSuccess(List<RightBean.DatasBean> list) {
                kindView.onSuccess(list);
            }

            @Override
            public void onFailure(String msg) {
                kindView.onFailure(msg);
            }
        });
    }
}
