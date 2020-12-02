package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;
import com.erhuo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping(value = "/comment",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getCom")
    public String getComment(int postId){
        List<CommentLite> comList = commentService.getComment(postId);
        String str = JSON.toJSONString(comList);
        System.out.println(str);
        return str;
    }

    @RequestMapping("/getLike")
    public String getLikeList(int userId){
        List<Integer> likeList = commentService.getLikeList(userId);
        String str = JSON.toJSONString(likeList);
        return str;
    }

    @RequestMapping("/like")
    public void Like(int userId,int comId){
        commentService.like(userId, comId);
    }

    @RequestMapping("/unlike")
    public void unLike(int userId,int comId){
        commentService.like(userId, comId);
    }


}
