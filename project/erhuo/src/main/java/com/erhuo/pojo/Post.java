package com.erhuo.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
    private int postId;
    private int userId;
    private String message;
    private String[] imagesUrl;
    private int viewNum;
    private int likeNum;
}
