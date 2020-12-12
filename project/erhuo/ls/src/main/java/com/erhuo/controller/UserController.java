package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.User;
import com.erhuo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.function.Consumer;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String login(String username,String password) {
        System.out.println(username+"    "+password);
        String login = userService.login(username, password);
        System.out.println(login);
        return login;
    }

    @RequestMapping("logon")
    @ResponseBody
    public String logon(String username,String password) {
        String logon = userService.logon(username, password);
        if(logon.equals("ok")) return userService.login(username, password);
        return logon;
    }

    @RequestMapping("/update")
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String username,String oldPassword,String newPassword) {
        return userService.updatePassword(username,oldPassword,newPassword);
    }
}
