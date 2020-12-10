package com.example.erhuo2.dsl.additem.entities;

public class CommodityImg {
    private int id;
    private int commodityId;
    private String img;

    public CommodityImg(int id, int commodityId, String img) {
        this.id = id;
        this.commodityId = commodityId;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
