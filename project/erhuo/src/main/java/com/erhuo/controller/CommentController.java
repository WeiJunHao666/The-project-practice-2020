package com.erhuo.controller;


import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;
import com.erhuo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/getCom",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    public String getComment(int postId){
        List<CommentLite> comList = commentService.getComment(postId);
        String str = JSON.toJSONString(comList);
        System.out.println(str);
        return str;
    }

}
