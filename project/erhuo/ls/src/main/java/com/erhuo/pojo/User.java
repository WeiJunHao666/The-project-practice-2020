package com.erhuo.pojo;

import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;
    private String username;
    private String nickname;
    private String phoneNum;
    private String email;
    private String password;
    private String img;
    private String address;

    public User(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.phoneNum = user.getPhoneNum();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.img = user.getImg();
        this.address = user.getAddress();
    }

}
