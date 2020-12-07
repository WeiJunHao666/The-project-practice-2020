package com.example.erhuo2.dsl.services.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private int comId;
    private int userId;
    private String name;
    private int img;
    private String prizes;
    private String content;
    private List<ReplyEntity> list = new ArrayList<>();
    private boolean isLike;
}
