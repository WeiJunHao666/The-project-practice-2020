package com.erhuo.service;

import com.erhuo.dao.CommentMapper;
import com.erhuo.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> getComment(int postId) {

        return null;
    }
}
