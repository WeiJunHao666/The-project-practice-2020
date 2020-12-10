package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentInfoToSer;
import com.erhuo.pojo.CommentLite;
import com.erhuo.service.CommentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@Controller
@RequestMapping(value = "/comment",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getCom/{postId}/{userId}")
    public String getComment(@PathVariable int postId,@PathVariable int userId){
        List<List<CommentLite>> comList = commentService.getComment(postId,userId);
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

    @RequestMapping("/like/{userId}/{comId}")
    public void Like(@PathVariable int userId, @PathVariable int comId){
        System.out.println(userId + " " + comId);
        commentService.like(userId, comId);
    }

    @RequestMapping("/unlike/{userId}/{comId}")
    public void unLike(@PathVariable int userId,@PathVariable int comId){
        System.out.println(userId + " " + comId);
        commentService.unLike(userId, comId);
    }

    @RequestMapping(value = "/addCom",method = RequestMethod.POST)
    public String addCom(@RequestBody String comment) throws IOException, JSONException {
        System.out.println(comment);
        CommentInfoToSer commentInfoToSer = JSON.parseObject(comment,CommentInfoToSer.class);
        Comment comment1 = new Comment();
        comment1.setPostId(commentInfoToSer.getPostId());
        comment1.setUserId(commentInfoToSer.getUserId());
        comment1.setMessage(commentInfoToSer.getContent());
        String result = commentService.addCom(comment1);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    public String reply(@RequestBody String reply){
        System.out.println(reply);
        CommentInfoToSer commentInfoToSer = JSON.parseObject(reply,CommentInfoToSer.class);
        Comment comment = new Comment();
        comment.setPostId(commentInfoToSer.getPostId());
        comment.setUserId(commentInfoToSer.getUserId());
        comment.setMessage(commentInfoToSer.getContent());
        comment.setLastId(commentInfoToSer.getLastComId());
        comment.setLastUserId(commentInfoToSer.getLastUserId());
        String result = commentService.reply(comment);
        System.out.println(result);
        return result;
    }



}
