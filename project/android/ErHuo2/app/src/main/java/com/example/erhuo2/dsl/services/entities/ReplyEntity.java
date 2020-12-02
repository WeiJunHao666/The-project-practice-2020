package com.example.erhuo2.dsl.services.entities;

public class ReplyEntity {
    private int comId;
    private int userId;
    private String name;
    private String prizes;
    private String content;


    public ReplyEntity(int comId, int userId, String name, String prizes, String content) {
        this.comId = comId;
        this.userId = userId;
        this.name = name;
        this.prizes = prizes;
        this.content = content;
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
