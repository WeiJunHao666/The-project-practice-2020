package com.erhuo.service;

import com.erhuo.dao.CommentMapper;
import com.erhuo.dao.PostMapper;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;
import com.erhuo.util.CommentUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<List<CommentLite>>  getComment(int postId,int userId) {
        postMapper.addView(postId);

        List<Comment> firstComment = commentMapper.queryLastIdNull(postId);
        List<Comment> thencomment = commentMapper.queryLastIdNotNull(postId);
        List<Integer> likeList = commentMapper.queryLikeCom(userId);
        System.out.println(likeList);
        List<Comment> comments = CommentUtil.addAllNode(firstComment, thencomment);
        List<CommentLite> comList = new ArrayList<CommentLite>();
        CommentUtil.getLite(comments,comList,likeList);
        List<List<CommentLite>> comLiteList = new ArrayList<List<CommentLite>>();
        int i = 0,len = comList.size();
        while(i < len){
            List<CommentLite> lites = new ArrayList<CommentLite>();
            lites.add(comList.get(i));
            i++;
            while(i < len && comList.get(i).getLastUser() != null){
                lites.add(comList.get(i));
                i++;
            }
            comLiteList.add(lites);
        }
        return comLiteList;
    }

    public List<Integer> getLikeList(int userId){
        List<Integer> likeList = commentMapper.queryLikeCom(userId);
        return likeList;
    }

    public void like(int userId,int comId){
        commentMapper.addLike(comId);
        commentMapper.addUserLike(userId, comId);
    }

    public void unLike(int userId,int comId){
        System.out.println(commentMapper.delLike(comId));
        System.out.println(commentMapper.delUserLike(userId,comId));
    }

    public String addCom(Comment comment1) {
        int result = commentMapper.addCom(comment1);
        int comId = comment1.getComId();
        System.out.println("comId:" + comId);
        String data = comId + "&" + result;
        return data;
    }

    public String reply(Comment comment) {
        int result = commentMapper.reply(comment);
        return null;
    }
}
