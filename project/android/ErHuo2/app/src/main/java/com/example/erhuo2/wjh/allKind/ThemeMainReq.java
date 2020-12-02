package com.example.erhuo2.wjh.allKind;

import java.io.Serializable;
import java.util.List;

public class ThemeMainReq implements Serializable{


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable{
        private String id;
        private String showName;
        private boolean chick;   //标识

        public boolean isChick() {
            return chick;
        }

        public void setChick(boolean chick) {
            this.chick = chick;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }
    }
}