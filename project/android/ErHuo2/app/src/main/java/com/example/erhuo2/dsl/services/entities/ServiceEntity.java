package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceEntity {
    private int img;
    private String name;
    private String content;
    private boolean check;
    private int prizes;
    private ArrayList<Integer> imgs = new ArrayList<>();
    //private ArrayList<String> imgs = new ArrayList<>();

    public boolean isCheck() {
        return check;
    }

    public ArrayList<Integer> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<Integer> imgs) {
        this.imgs = imgs;
    }

    public int getPrizes() {
        return prizes;
    }

    public void setPrizes(int prizes) {
        this.prizes = prizes;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public ServiceEntity(int img, String name, String content, boolean check, int prizes, ArrayList<Integer> imgs) {
        this.img = img;
        this.name = name;
        this.content = content;
        this.check = check;
        this.prizes = prizes;
        this.imgs = imgs;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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
}
