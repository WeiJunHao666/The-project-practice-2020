package com.erhuo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    private int postId;
    private int userId;
    private String userName;
    private String userImg;
    private String postMes;
    private String postDate;
    private List<String> imgs = new ArrayList<>();
    private int viewNum;
    private int likeNum;
    private Boolean isLike;
}
