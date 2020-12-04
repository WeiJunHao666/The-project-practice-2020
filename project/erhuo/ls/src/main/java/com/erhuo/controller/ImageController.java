package com.erhuo.controller;

import com.erhuo.util.QiniuUpload;
import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/img")
public class ImageController {

    @RequestMapping("/token")
    @ResponseBody
    public String getToken(){
        System.out.println(QiniuUpload.getUpToken());
        return QiniuUpload.getUpToken();
    }
}
