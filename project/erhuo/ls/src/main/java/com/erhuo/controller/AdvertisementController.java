package com.erhuo.controller;

import com.erhuo.pojo.Advertisement;
import com.erhuo.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.*;

import java.util.List;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/all",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String getAdvertisementAll(){
        String s = advertisementService.queryAdvertisementAll();
        return s;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAdvertisement(@RequestBody Advertisement advertisement){
        advertisementService.addAdvertisement(advertisement);
    }
}
