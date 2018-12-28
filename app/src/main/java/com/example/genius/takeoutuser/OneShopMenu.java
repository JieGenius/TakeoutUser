package com.example.genius.takeoutuser;

import java.util.List;

public class OneShopMenu {

    private List<MenuBean> menu;

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * num : 501
         * name : 二荤任选套餐
         * introduce : 全素菜，金针菇，任选热饮
         * price : 12
         * discount : 0.60
         * sales : 39
         * C_num : 5
         */

        private String num;
        private String name;
        private String introduce;
        private String price;
        private String discount;
        private String sales;
        private String C_num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getC_num() {
            return C_num;
        }

        public void setC_num(String C_num) {
            this.C_num = C_num;
        }
    }
}
