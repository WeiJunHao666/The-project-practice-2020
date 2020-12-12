package com.erhuo.dao;

import com.erhuo.pojo.SubType;
import com.erhuo.pojo.SuperType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {

    public List<SuperType> findSuperTypeAll();

    public List<SubType> findSubTypeById(@Param("id") int id);
}
