package com.erhuo.pojo;

import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String username;
    private String nickname;
    private String Img;
}
