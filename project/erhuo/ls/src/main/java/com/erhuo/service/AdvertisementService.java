package com.erhuo.service;

import com.alibaba.fastjson.JSON;
import com.erhuo.dao.AdvertisementMapper;
import com.erhuo.pojo.Advertisement;
import com.erhuo.pojo.CommodityImg;
import com.erhuo.util.JedisUtil;
import com.erhuo.util.VariableName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    public String queryAdvertisementAll(){
        String advertisement = JedisUtil.getInstance().STRINGS.get("advertisement");
        if(advertisement == null) {
            List<Advertisement> list = advertisementMapper.queryAdvertisementAll();
            for (Advertisement advertisement1 : list) {
                String s = advertisement1.getImg();
                advertisement1.setImg(VariableName.domain+"/"+s);
            }
            String s = JSON.toJSONString(list);
            JedisUtil.getInstance().STRINGS.set("advertisement",s);
            return s;
        }
        return advertisement;
    }

    public void addAdvertisement(Advertisement advertisement){
        advertisementMapper.addAdvertisement(advertisement);
    }
}
