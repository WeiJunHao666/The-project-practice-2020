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
    private int sex;
    private String address;

}
