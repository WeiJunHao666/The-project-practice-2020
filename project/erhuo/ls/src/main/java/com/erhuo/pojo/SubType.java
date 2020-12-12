package com.erhuo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubType {
    private int typeId;
    private String typeName;
    private String img;
}
