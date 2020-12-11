package com.example.erhuo2.wjh.allKind.bean;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class RightBean implements Serializable{
    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable {
        private String title;
        private List<ListBean> list;
        private boolean chick;   //标识

        public boolean isChick() {
            return chick;
        }

        public void setChick(boolean chick) {
            this.chick = chick;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setList(List<ListBean> list){
            this.list = list;
        }

        public List<ListBean> getList(){
            return list;
        }

        public static class ListBean implements Serializable{

            private ImageView img;
            private String name;

            public void setImg(ImageView img){
                this.img = img;
            }
            public ImageView getImg(){
                return img;
            }
            public void setName(String name){
                this.name = name;
            }
            public String getName(){
                return name;
            }

        }

    }
}
