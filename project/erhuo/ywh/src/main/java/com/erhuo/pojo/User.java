package com.erhuo.pojo;

import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String userName;
    private String nickName;
    private String Img;
}
