package com.erhuo.dao;

import com.erhuo.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    public List<Commodity> findAll(@Param("st") int st,@Param("ed") int ed, @Param("city") String city);

    public Commodity findCommodityById(@Param("id") int id);

    public List<Commodity> findCommodityByName(@Param("name") String name,@Param("city") String city);

    public void addCommodity(Commodity commodity);

    public List<Commodity> searchCommodityByType(@Param("typeId") int typeId,
                                                 @Param("st") int st,@Param("ed") int ed,
                                                 @Param("city") String city);

    public List<Commodity> searchSimilarCommodityByType(@Param("typeId") int typeId,
                                                        @Param("st") int st,
                                                        @Param("ed") int ed,
                                                        @Param("commodityId") int commodityId,
                                                        @Param("city") String city);

    public List<Integer> searchNumberSome();

    public void deleteCommodityById(@Param("id") int id,@Param("username") String username,@Param("password") String password);

    public void store(@Param("buyerId") int buyerId, @Param("sellerId") int sellerId, @Param("commodityId") int commodityId);

    public String getStore(@Param("buyerId") int buyerId,@Param("commodityId") int commodityId);

    public void deleteStore(@Param("buyerId") int buyerId,@Param("commodityId") int commodityId);

    public List<Commodity> screenCommodity(@Param("name") String name,
                                @Param("typeId") Integer typeId,
                                @Param("minValue") Integer minValue,
                                @Param("maxValue") Integer maxValue,
                                @Param("nowTime") Long nowTime,
                                @Param("time") Integer time,
                                @Param("oldOrNew") Integer oldOrNew,
                                @Param("st") Integer st,
                                @Param("ed") Integer ed,
                                @Param("order") Integer order,
                                @Param("city") String city);

    public List<Commodity> myCommodity(@Param("userId") int userId, @Param("st") int st, @Param("ed") int ed);

    public List<Commodity> myStore(@Param("userId") int userId, @Param("st") int st, @Param("ed") int ed);
}
