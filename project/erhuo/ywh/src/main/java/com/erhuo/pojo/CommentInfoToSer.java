package com.erhuo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentInfoToSer {
    private int userId;
    private int postId;
    private String content;
    private int lastComId;
    private int lastUserId;
}
