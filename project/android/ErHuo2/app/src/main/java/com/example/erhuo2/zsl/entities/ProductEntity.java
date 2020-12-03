package com.example.erhuo2.zsl.entities;

public class ProductEntity {
    private int img;
    private String describe;
    private String price;
    private String seller;
    private String position;
    public ProductEntity(int img, String describe, String price , String seller, String position){
        this.img = img;
        this.describe = describe;
        this.price = price;
        this.seller = seller;
        this.position = position;
    }
    public int getImg() {
        return img;
    }

    public String getDescribe() {
        return describe;
    }

    public String getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public String getPosition() {
        return position;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
