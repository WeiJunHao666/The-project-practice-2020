package com.example.erhuo2.dsl.services.view;

import com.example.erhuo2.dsl.services.entities.ServiceEntity;

import java.util.List;

public interface GetPostView {
    void onSuccess(List<ServiceEntity> list);
    void onFailed(String s);
}
