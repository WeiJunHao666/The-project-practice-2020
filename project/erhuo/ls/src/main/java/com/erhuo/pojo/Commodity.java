package com.erhuo.pojo;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    private int id;
    private int userId;
    private User user;
    private float value;
    private int discount;
    private String describe;
    private int typeId;
    private long time;
    private int oldStandard;
    private List<CommodityImg> img;
}
