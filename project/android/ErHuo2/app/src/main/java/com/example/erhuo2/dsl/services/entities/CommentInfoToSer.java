package com.example.erhuo2.dsl.services.entities;

public class CommentInfoToSer {
    private int userId;      //用户id
    private int postId;      //帖子id
    private String content;  //回复内容
    private int lastComId;   //回复上条评论id
    private int lastUserId;  //回复上个人id

    public CommentInfoToSer(int userId, int postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public CommentInfoToSer(int userId, int postId, String content, int lastComId, int lastUserId) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.lastComId = lastComId;
        this.lastUserId = lastUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
