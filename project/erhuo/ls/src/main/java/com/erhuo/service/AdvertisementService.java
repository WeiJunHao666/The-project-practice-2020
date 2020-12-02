package com.erhuo.service;

import com.erhuo.dao.AdvertisementMapper;
import com.erhuo.pojo.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    public List<Advertisement> queryAdvertisementAll(){
        List<Advertisement> list = advertisementMapper.queryAdvertisementAll();
        return list;
    }

    public void addAdvertisement(Advertisement advertisement){
        advertisementMapper.addAdvertisement(advertisement);
    }
}
