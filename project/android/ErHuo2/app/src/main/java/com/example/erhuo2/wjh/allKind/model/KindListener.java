package com.example.erhuo2.wjh.allKind.model;


import com.example.erhuo2.wjh.allKind.bean.RightBean;

import java.util.List;

public interface KindListener {
    void onSuccess(List<RightBean.DatasBean> list);
    void onFailure(String msg);
}
