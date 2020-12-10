package com.example.erhuo2.dsl.additem.entities;

import java.util.List;

public class Commodity{
    private int id;
    private String userId;
    private float value;
    private String describe;
    private int typeId;
    private long time;
    private int oldStandard;
    private List<CommodityImg> img;

    public Commodity(int id, String userId, float value, String describe, int typeId, long time, int oldStandard, List<CommodityImg> img) {
        this.id = id;
        this.userId = userId;
        this.value = value;
        this.describe = describe;
        this.typeId = typeId;
        this.time = time;
        this.oldStandard = oldStandard;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getOldStandard() {
        return oldStandard;
    }

    public void setOldStandard(int oldStandard) {
        this.oldStandard = oldStandard;
    }

    public List<CommodityImg> getImg() {
        return img;
    }

    public void setImg(List<CommodityImg> img) {
        this.img = img;
    }
}