package com.example.erhuo2.wjh.setInfo.bean;

public class InfoData {
    private int userId;
    private String img;

    public InfoData(int userId, String img) {
        this.userId = userId;
        this.img = img;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
