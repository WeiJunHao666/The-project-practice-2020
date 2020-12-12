package com.erhuo.service;

import com.alibaba.fastjson.JSON;
import com.erhuo.dao.TypeMapper;
import com.erhuo.pojo.SubType;
import com.erhuo.pojo.SuperType;
import com.erhuo.util.JedisUtil;
import com.erhuo.util.VariableName;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;

public class TypeService {
    @Autowired
    private TypeMapper typeMapper;

    public String findTypeAll() {
        String type = JedisUtil.getInstance().STRINGS.get("type");
        if(type==null) {
            List<SuperType> list = typeMapper.findSuperTypeAll();
            System.out.println(list);
            for (SuperType superType : list) {
                int id = superType.getTypeId();
                List<SubType> subTypeList = typeMapper.findSubTypeById(id);
                for (SubType subType : subTypeList) {
                    String img = subType.getImg();
                    subType.setImg(VariableName.domain+"/"+img);
                }
                superType.setList(subTypeList);
            }
            String s = JSON.toJSONString(list);
            JedisUtil.getInstance().STRINGS.set("type",s);
            return s;
        }
        return type;
    }
}
