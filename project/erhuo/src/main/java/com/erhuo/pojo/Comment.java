package com.erhuo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int comId;
    private int userId;
    private int postId;
    private int lastId;
    private String message;

    private List<Comment> nextComments;
}
