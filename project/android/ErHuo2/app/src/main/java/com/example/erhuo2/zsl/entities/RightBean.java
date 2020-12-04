package com.example.erhuo2.zsl.entities;

import java.io.Serializable;
import java.util.List;

public class RightBean implements Serializable {
    private List<AdvertisingEntity> datas;

    public List<AdvertisingEntity> getDatas() {
        return datas;
    }

    public void setDatas(List<AdvertisingEntity> datas) {
        this.datas = datas;
    }
    public static class AdvertisingEntity implements Serializable{
        private int id;
        private String fileName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

}
