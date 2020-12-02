package com.erhuo.service;

import com.erhuo.dao.CommentMapper;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;
import com.erhuo.util.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private CommentMapper commentMapper;

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<CommentLite> getComment(int postId) {
        List<Comment> firstComment = commentMapper.queryLastIdNull(postId);
        List<Comment> comments = commentMapper.queryLastIdNotNull(postId);

        List<Comment> commentList = CommentUtil.addAllNode(firstComment, comments);
        List<CommentLite> comList = new ArrayList<CommentLite>();
        CommentUtil.getLite(commentList,comList);

        return comList;
    }
}
