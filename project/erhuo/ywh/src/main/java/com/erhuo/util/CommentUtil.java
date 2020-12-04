package com.erhuo.util;

import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;

import java.util.List;

public class CommentUtil {

    public static boolean addNode(List<Comment> list, Comment node){
        for (Comment node1 : list) {   //循环添加
            if (node1.getComId()==node.getLastId()){   //判断留言的上一段是都是这条留言
                node1.getNextComments().add(node);   //是，添加，返回true;
                return true;
            }else{     //否则递归继续判断
                if (node1.getNextComments().size()!=0)
                    if (addNode(node1.getNextComments(),node)){
                        return true;
                    }
            }
        }
        return false;
    }

    /**
     * 将查出来的lastId不为null的回复都添加到第一层Node集合中
     * @param firstList
     * @param thenList
     * @return
     */
    public static List<Comment> addAllNode(List<Comment> firstList, List<Comment> thenList){
        while (thenList.size()!=0){
            int size = thenList.size();
            for (int i = 0;i<size;i++){
                if (addNode(firstList,thenList.get(i))){
                    thenList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return firstList;
    }

    public static void getLite(List<Comment> list, List<CommentLite> comList,List<Integer> likeList){
        for (Comment node : list) {
            CommentLite commentLite = new CommentLite();
            commentLite.setComId(node.getComId());
            commentLite.setComUser(node.getComUser());
            commentLite.setLastUser(node.getLastUser());
            commentLite.setMessage(node.getMessage());
            commentLite.setUserId(node.getUserId());
            commentLite.setLikeNum(node.getLikeNum());
            if(likeList.contains(node.getComId())){
                commentLite.setIsLike(true);
            }
            comList.add(commentLite);
            if (node.getNextComments().size()!=0){
                getLite(node.getNextComments(),comList,likeList);
            }
        }
    }
}
