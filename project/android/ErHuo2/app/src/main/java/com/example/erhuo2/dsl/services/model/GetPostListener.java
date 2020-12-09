package com.example.erhuo2.dsl.services.model;

import com.example.erhuo2.dsl.services.entities.ServiceEntity;

import java.util.List;

public interface GetPostListener {
    void onSuccess(List<ServiceEntity> list);
    void onFailed(String s);
}
