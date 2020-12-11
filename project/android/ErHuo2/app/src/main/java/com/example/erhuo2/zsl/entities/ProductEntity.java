package com.example.erhuo2.zsl.entities;

import java.util.List;

public class ProductEntity {
    private int id;
    private List<ProductImgEntity> img;
    private String describe;
    private String value;
    private int userId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    public ProductEntity(int id,User user,List<ProductImgEntity> img, String describe, String price , int seller, String position){
        this.id = id;
        this.user = user;
        this.img = img;
        this.describe = describe;
        this.value = price;
        this.userId = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductImgEntity> getImg() {
        return img;
    }

    public void setImg(List<ProductImgEntity> img) {
        this.img = img;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", img=" + img +
                ", describe='" + describe + '\'' +
                ", value='" + value + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}
