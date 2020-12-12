package com.erhuo.dao;

import com.erhuo.pojo.SubType;
import com.erhuo.pojo.SuperType;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class TypeMapperImpl extends SqlSessionDaoSupport implements TypeMapper{
    @Override
    public List<SuperType> findSuperTypeAll() {
        TypeMapper typeMapper = getSqlSession().getMapper(TypeMapper.class);
        List<SuperType> list = typeMapper.findSuperTypeAll();
        return list;
    }

    @Override
    public List<SubType> findSubTypeById(int id) {
        TypeMapper typeMapper = getSqlSession().getMapper(TypeMapper.class);
        List<SubType> list = typeMapper.findSubTypeById(id);
        return list;
    }
}
