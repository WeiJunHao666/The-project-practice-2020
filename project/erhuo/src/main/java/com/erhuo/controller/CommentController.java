package com.erhuo.controller;


import com.erhuo.pojo.Comment;
import com.erhuo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/getCom")
    public List<Comment> getComment(int postId){
        List<Comment> commentList = commentService.getComment(postId);

        return commentList;
    }

}
