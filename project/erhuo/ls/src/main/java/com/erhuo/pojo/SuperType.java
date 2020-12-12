package com.erhuo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperType {
    private int typeId;
    private String typeName;
    private List<SubType> list;
}
