package com.erhuo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int comId;
    private int userId;
    private int postId;
    private int lastId;
    private int lastUserId;
    private String message;
    private String lastUser;
    private String comUser;
    private List<Comment> nextComments = new ArrayList<Comment>();
    private int likeNum;
    private String userImg;
}
