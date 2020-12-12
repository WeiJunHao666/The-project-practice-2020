package com.erhuo.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityImg {
    private int id;
    private int commodityId;
    private String img;
}
