package com.example.erhuo2.zsl.entities;

public class User {
    private int userId;
    private String username;
    private String nickname;
    private String phoneNum;
    private String email;
    private String password;
    private String img;
    private String address;

    public User(int userId,String username,String nickname,String phoneNum,String email,String password,String img,String address){
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.img = img;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
