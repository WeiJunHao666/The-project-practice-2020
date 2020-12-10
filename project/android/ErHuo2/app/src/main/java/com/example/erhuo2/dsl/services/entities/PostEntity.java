package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;
import java.util.List;


public class PostEntity {

    private int postId;
    private int userId;
    private String userName;
    private String userImg;
    private String postMes;
    private String postDate;
    private List<String> imgs = new ArrayList<>();
    private int viewNum;
    private int likeNum;
    private Boolean isLike;

    public PostEntity(int postId, int userId, String userName, String userImg, String postMes, String postDate, List<String> imgs, int viewNum, int likeNum, Boolean isLike) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.userImg = userImg;
        this.postMes = postMes;
        this.postDate = postDate;
        this.imgs = imgs;
        this.viewNum = viewNum;
        this.likeNum = likeNum;
        this.isLike = isLike;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPostMes() {
        return postMes;
    }

    public void setPostMes(String postMes) {
        this.postMes = postMes;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
