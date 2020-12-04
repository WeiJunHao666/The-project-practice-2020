package com.erhuo.pojo;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Commodity {
    private int id;
    private String userId;
    private float value;
    private int discount;
    private String describe;
    private int typeId;
    private long time;
    private int oldStandard;
    private List<CommodityImg> img;
}
