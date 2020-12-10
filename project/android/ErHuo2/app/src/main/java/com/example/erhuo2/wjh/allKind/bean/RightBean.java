package com.example.erhuo2.wjh.allKind.bean;

import java.io.Serializable;

public class RightBean implements Serializable{
    //        private String title;
    private boolean chick;   //标识
    private int typeId;
    private String typeName;
    private String img;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isChick() {
        return chick;
    }

    public void setChick(boolean chick) {
        this.chick = chick;
    }

//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
}
