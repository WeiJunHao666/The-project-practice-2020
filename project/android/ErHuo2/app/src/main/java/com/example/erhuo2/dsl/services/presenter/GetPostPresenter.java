package com.example.erhuo2.dsl.services.presenter;

import com.example.erhuo2.dsl.services.entities.ServiceEntity;
import com.example.erhuo2.dsl.services.model.GetPostListener;
import com.example.erhuo2.dsl.services.model.ServiceModel;
import com.example.erhuo2.dsl.services.view.GetPostView;

import java.util.List;

public class GetPostPresenter {
    private GetPostView gpv;
    public GetPostPresenter(GetPostView gpv){
        this.gpv = gpv;
    }
    public void getData(int pageNum){
        ServiceModel.getInstance().getServiceDate(pageNum, new GetPostListener() {
            @Override
            public void onSuccess(List<ServiceEntity> list) {
                gpv.onSuccess(list);
            }

            @Override
            public void onFailed(String s) {
                gpv.onFailed(s);
            }
        });
    }
}
