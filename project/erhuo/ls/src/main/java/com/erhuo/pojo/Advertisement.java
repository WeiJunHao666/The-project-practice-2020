package com.erhuo.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Advertisement {
    private int id;
    private String img;
}
