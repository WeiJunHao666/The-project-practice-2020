package com.erhuo.dao;

import com.erhuo.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    public List<Commodity> findAll();

    public Commodity findCommodityById(@Param("id") int id);
}
