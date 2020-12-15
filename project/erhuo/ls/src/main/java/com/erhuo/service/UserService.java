package com.erhuo.service;

import com.alibaba.fastjson.JSON;
import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.School;
import com.erhuo.pojo.User;
import com.erhuo.util.JedisUtil;
import com.erhuo.util.VariableName;
import com.qiniu.util.Json;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String login(String username,String password){
        User user = userMapper.login(username, password);
        if(user!=null) {
            String s = user.getImg();
            user.setImg(VariableName.domain + "/" + s);
            userMapper.updateDate(user.getUserId(),new Date());
        }
        if(user==null) {
            int num = userMapper.checkUserByUsername(username);
            if(num==0) {
                return "usernameError";
            } else {
                return "passwordError";
            }
        }
        return JSON.toJSONString(user);
    }

    public String logon(String username,String password) {
        int num = userMapper.checkUserByUsername(username);
        if(num!=0) {
            return "error";
        }
        userMapper.logon(username,password);
        return "ok";
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public String  updatePassword(String username,String oldPassword,String newPassword){
        User user = userMapper.login(username, oldPassword);
        if (user!=null) {
            userMapper.updatePassword(username, oldPassword, newPassword);
            return "ok";
        } else {
            return "error";
        }
    }

    public String map(String school){
        school = school==null?null:"%"+school+"%";
        System.out.println(school);
        String mapStr = JedisUtil.getInstance().STRINGS.get("map_"+school);
        if(mapStr==null) {
            List<School> map = userMapper.map(school);
            String s = JSON.toJSONString(map);
            JedisUtil.getInstance().STRINGS.set("map_"+school,s);
            return s;
        }
        return mapStr;
    }

    public String city(){
        String cityStr = JedisUtil.getInstance().STRINGS.get("city");
        if(cityStr==null) {
            List<School> city = userMapper.city();
            String s = JSON.toJSONString(city);
            JedisUtil.getInstance().STRINGS.set("city",s);
            return s;
        }
        return cityStr;
    }

    public String getNickname(List<String> user) {
        List<String> list = new ArrayList<>();
        for (String s : user) {
            String nickname = userMapper.findNicknameByUsername(s);
            list.add(nickname);
        }
        return JSON.toJSONString(list);
    }

    public String seller(int userId) {
        User seller = userMapper.seller(userId);
        seller.setImg(VariableName.domain+"/"+seller.getImg());
        return JSON.toJSONString(seller);
    }
}
