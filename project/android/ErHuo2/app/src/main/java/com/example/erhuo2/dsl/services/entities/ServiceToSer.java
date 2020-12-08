package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceToSer {
    private int userId;     //用户id
    private String content; //帖子内容
    private List<String> imgs = new ArrayList<>();  //图片
    private String date;    //发布日期

    public ServiceToSer(int userId, String content, List<String> imgs, String date) {
        this.userId = userId;
        this.content = content;
        this.imgs = imgs;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
