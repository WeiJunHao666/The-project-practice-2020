package com.example.erhuo2.dsl.services.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComInfoEntity {
    private int comId;
    private String comUser;
    private String lastUser;
    private boolean isLike;
    private int likeNum;
    private String message;
    private int userId;
}
