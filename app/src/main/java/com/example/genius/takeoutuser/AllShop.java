package com.example.genius.takeoutuser;

import java.util.List;

public class AllShop {

    private List<ShopArrBean> shopArr;

    public List<ShopArrBean> getShopArr() {
        return shopArr;
    }

    public void setShopArr(List<ShopArrBean> shopArr) {
        this.shopArr = shopArr;
    }

    public static class ShopArrBean {
        /**
         * id : 1
         * name : 张姐炒方便面加挂面
         * phone : 15364719590
         * address : 小店区北营街办许西北街107号
         * grade : 4.3
         * time : 10:00-22:00
         * scales : 1626
         * state : 2
         */

        private String id;
        private String name;
        private String phone;
        private String address;
        private String grade;
        private String time;
        private String scales;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getScales() {
            return scales;
        }

        public void setScales(String scales) {
            this.scales = scales;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
