package com.example.erhuo2.zsl.entities;

import java.util.List;

public class ProductEntity {
    private List<ProductImgEntity> img;
    private String describe;
    private String value;
    private String userId;
    public ProductEntity(List<ProductImgEntity> img, String describe, String value , String userId){
        this.img = img;
        this.describe = describe;
        this.value = value;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
