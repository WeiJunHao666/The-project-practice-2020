package com.example.erhuo2.dsl.services.entities;

public class ServiceEntity {
    private int img;
    private String name;
    private String content;
    private boolean check;
    private int prizes;

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
//private List<Bitmap> imgs = new ArrayList<>();

    public ServiceEntity(int img, String name, String content, boolean check, int prizes) {
        this.img = img;
        this.name = name;
        this.content = content;
        this.check = check;
        this.prizes = prizes;
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
