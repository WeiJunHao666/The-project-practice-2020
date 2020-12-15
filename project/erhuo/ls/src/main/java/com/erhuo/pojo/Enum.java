package com.erhuo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enum {
    private String name;  // 搜索的商品名
    private Integer typeId;  //分类id
    private Integer value;  //价格区间 0：0-300 1：300-1000 2：1000->
    private Integer time;  //上传时间 0：一天内 1：一周内 2：一个月内
    private Integer oldOrNew;  //新旧程度 0：全新 1：99新 2：其他
    private Integer page;
    private Integer order;  // 0：收藏数多到少 1：时间由进到远 2：时间由远到近 3：价格由高到低 4：价格由低到高
    private String city;
}
