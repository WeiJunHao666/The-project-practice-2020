package com.example.erhuo2.wjh.setInfo.model;

public interface Model {
    void setInfo(String s, ModelListener listener);
    void sendName(String s, String json, ModelListener listener);
}
