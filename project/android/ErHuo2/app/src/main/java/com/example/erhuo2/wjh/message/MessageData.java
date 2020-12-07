package com.example.erhuo2.wjh.message;

public class MessageData {
    private int headImage;
    private String name;
    private String info;
    private String time;
    private int commodityImage;

    public MessageData(int headImage, String name, String info, String time, int commodityImage) {
        this.headImage = headImage;
        this.name = name;
        this.info = info;
        this.time = time;
        this.commodityImage = commodityImage;
    }

    public int getHeadImage() {
        return headImage;
    }

    public void setHeadImage(int headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommodityImage() {
        return commodityImage;
    }

    public void setCommodityImage(int commodityImage) {
        this.commodityImage = commodityImage;
    }
}
