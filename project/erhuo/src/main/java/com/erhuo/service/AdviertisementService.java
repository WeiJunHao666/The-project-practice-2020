package com.erhuo.service;

import com.erhuo.dao.AdviertisementMapper;
import com.erhuo.pojo.Adviertisement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdviertisementService {
    @Autowired
    private AdviertisementMapper adviertisementMapper;

    public List<Adviertisement> queryAdviertisementAll(){
        List<Adviertisement> list =adviertisementMapper.queryAdviertisementAll();
        return list;
    }

    public void addAdviertisement(Adviertisement adviertisement){
        adviertisementMapper.addAdviertisement(adviertisement);
    }
}
