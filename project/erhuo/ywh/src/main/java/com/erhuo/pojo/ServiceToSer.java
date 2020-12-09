package com.erhuo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ServiceToSer {
    private int userId;     //用户id
    private String content; //帖子内容
    private List<String> imgs = new ArrayList<>();  //图片
    private String date;    //发布日期

    public ServiceToSer(int userId, String content, List<String> imgs, String date) {
        this.userId = userId;
        this.content = content;
        this.imgs = imgs;
        this.date = date;
    }

}
