package com.erhuo.dao;

import com.erhuo.pojo.Adviertisement;

import java.util.List;

public interface AdviertisementMapper {
    public List<Adviertisement> queryAdviertisementAll();

    public void addAdviertisement(Adviertisement adviertisement);
}
