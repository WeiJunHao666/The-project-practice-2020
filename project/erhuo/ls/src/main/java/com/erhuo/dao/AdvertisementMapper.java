package com.erhuo.dao;

import com.erhuo.pojo.Advertisement;

import java.util.List;

public interface AdvertisementMapper {
    public List<Advertisement> queryAdvertisementAll();

    public void addAdvertisement(Advertisement advertisement);
}
