package com.example.erhuo2.wjh.allKind.bean;

import java.util.List;

public class TitleBean {
    private int typeId;
    private List<String> title;

    public TitleBean(int typeId, List<String> title) {
        this.typeId = typeId;
        this.title = title;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public List getTitle() {
        return title;
    }

    public void setTitle(List title) {
        this.title = title;
    }
}
