package com.erhuo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentLite {
    private int comId;
    private int userId;
    private String message;
    private String lastUser;
    private String comUser;
}
