package com.example.erhuo2.dsl.services.entities;

public class ComInfoEntity {
    private int comId;
    private String comUser;
    private String lastUser;
    private boolean isLike;
    private int likeNum;
    private String message;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    private int userId;

    public ComInfoEntity(int comId, String comUser, String lastUser, int likeNum, String message, int userId) {
        this.comId = comId;
        this.comUser = comUser;
        this.lastUser = lastUser;
        this.likeNum = likeNum;
        this.message = message;
        this.userId = userId;
        this.isLike = isLike;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComUser() {
        return comUser;
    }

    public void setComUser(String comUser) {
        this.comUser = comUser;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
