package com.example.erhuo2.wjh.allKind.view;


import com.example.erhuo2.wjh.allKind.bean.RightBean;

import java.util.List;

public interface KindView {
    void onSuccess(List<RightBean.DatasBean> list);
    void onFailure(String msg);
}
