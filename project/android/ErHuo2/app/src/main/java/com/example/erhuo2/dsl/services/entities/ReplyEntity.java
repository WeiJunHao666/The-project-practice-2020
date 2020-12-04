package com.example.erhuo2.dsl.services.entities;

public class ReplyEntity {
    private int comId;
    private int userId;
    private String name;
    private String prizes;
    private String content;
    private boolean isLike;


    public ReplyEntity(int comId, int userId, String name, String prizes, String content, boolean isLike) {
        this.comId = comId;
        this.userId = userId;
        this.name = name;
        this.prizes = prizes;
        this.content = content;
        this.isLike = isLike;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
