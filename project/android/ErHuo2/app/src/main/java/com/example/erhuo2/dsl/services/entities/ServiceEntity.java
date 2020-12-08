package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;

public class ServiceEntity {
    private int postId;  //帖子id
    private int userId;  //用户id
    private String img;  //用户头像
    private String name;  //用户名字
    private String content; //帖子内容
    private String date;  //日期
    private boolean check; //是否点赞
    private int prizes;   //点赞数
    private int pageview;  //浏览量
    //private ArrayList<Integer> imgs = new ArrayList<>();
    private ArrayList<String> imgs = new ArrayList<>();  //图片列表

    public ServiceEntity(int postId, int userId, String img, String name, String content, String date, boolean check, int prizes, int pageview, ArrayList<String> imgs) {
        this.postId = postId;
        this.userId = userId;
        this.img = img;
        this.name = name;
        this.content = content;
        this.date = date;
        this.check = check;
        this.prizes = prizes;
        this.pageview = pageview;
        this.imgs = imgs;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getPrizes() {
        return prizes;
    }

    public void setPrizes(int prizes) {
        this.prizes = prizes;
    }

    public int getPageview() {
        return pageview;
    }

    public void setPageview(int pageview) {
        this.pageview = pageview;
    }

    public ArrayList<String> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }
}
