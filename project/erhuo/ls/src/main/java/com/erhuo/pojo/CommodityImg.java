package com.erhuo.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CommodityImg {
    private int id;
    private int commodityId;
    private String img;
}
