package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;
import java.util.List;

public class CommentEntity {
    private int comId;
    private int userId;
    private String name;
    private int img;
    private String prizes;
    private String content;
    private List<ReplyEntity> list = new ArrayList<>();

    public CommentEntity(int comId, int userId, String name, int img, String prizes, String content, List<ReplyEntity> list) {
        this.comId = comId;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.prizes = prizes;
        this.content = content;
        this.list = list;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public List<ReplyEntity> getList() {
        return list;
    }

    public void setList(List<ReplyEntity> list) {
        this.list = list;
    }
}
