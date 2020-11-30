package com.example.erhuo2.dsl.services.entities;

public class ReplyEntity {
    private String name;
    private String prizes;
    private String content;

    public ReplyEntity(String name, String prizes, String content) {
        this.name = name;
        this.prizes = prizes;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
