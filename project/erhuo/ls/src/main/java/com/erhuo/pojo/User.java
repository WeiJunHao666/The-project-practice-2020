package com.erhuo.pojo;

import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private int userId;
    private String username;
    private String nickname;
    private String phoneNum;
    private String email;
    private String password;
    private String img;
    private String address;

}
