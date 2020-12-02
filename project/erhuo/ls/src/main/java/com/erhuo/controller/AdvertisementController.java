package com.erhuo.controller;

import com.erhuo.pojo.Advertisement;
import com.erhuo.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.*;

import java.util.List;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getAdvertisementAll(){
        List<Advertisement> list = advertisementService.queryAdvertisementAll();
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAdvertisement(Advertisement advertisement){
        System.out.println(advertisement);
        advertisementService.addAdvertisement(advertisement);
    }
}
