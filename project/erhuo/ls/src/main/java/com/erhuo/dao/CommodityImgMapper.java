package com.erhuo.dao;

import com.erhuo.pojo.CommodityImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityImgMapper {
    public List<CommodityImg> findImgById(@Param("commodityId") int id);
}
