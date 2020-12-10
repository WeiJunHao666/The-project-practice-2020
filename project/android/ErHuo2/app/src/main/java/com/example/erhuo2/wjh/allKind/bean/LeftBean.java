package com.example.erhuo2.wjh.allKind.bean;

import java.io.Serializable;
import java.util.List;

public class LeftBean implements Serializable{

    private String typeId;
    private String typeName;
    private List<RightBean> list;
    private boolean chick;   //标识

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isChick() {
        return chick;
    }

    public void setChick(boolean chick) {
        this.chick = chick;
    }

    public List<RightBean> getList() {
        return list;
    }

    public void setList(List<RightBean> list) {
        this.list = list;
    }
}