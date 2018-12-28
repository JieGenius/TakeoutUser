package com.example.genius.takeoutuser;

import java.util.List;

public class OrderInfo {

    private List<AllOrderBean> allOrder;

    public List<AllOrderBean> getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(List<AllOrderBean> allOrder) {
        this.allOrder = allOrder;
    }

    public static class AllOrderBean {
        /**
         * orderNum : 2100453172
         * orderSum : 13
         * orderTime : 2018-10-27 11:12:00
         * orderDeliveryFee : 1
         * orderBoxFee : 1
         * deliveryPhone : 18266593355
         * shopPhone : 17324590258
         * shopAddress : 万柏林区和平南路192号
         * shopname : 过桥米线
         * commentContent : 刚开始觉得还可以，今天的量不够也就算了，感觉饭里面有一种怪怪的味道。
         * commentGrade : 刚开始觉得还可以，今天的量不够也就算了，感觉饭里面有一种怪怪的味道。
         * allMeal : [{"name":"无辣不欢组合","count":"1"},{"name":"童子炸鸡","count":"2"},{"name":"孜然牛肉炒面套餐","count":"1"}]
         */

        private String orderNum;
        private String orderSum;
        private String orderTime;
        private String orderDeliveryFee;
        private String orderBoxFee;
        private String deliveryPhone;
        private String shopPhone;
        private String shopAddress;
        private String shopname;
        private String commentContent;
        private String commentGrade;
        private List<AllMealBean> allMeal;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getOrderSum() {
            return orderSum;
        }

        public void setOrderSum(String orderSum) {
            this.orderSum = orderSum;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getOrderDeliveryFee() {
            return orderDeliveryFee;
        }

        public void setOrderDeliveryFee(String orderDeliveryFee) {
            this.orderDeliveryFee = orderDeliveryFee;
        }

        public String getOrderBoxFee() {
            return orderBoxFee;
        }

        public void setOrderBoxFee(String orderBoxFee) {
            this.orderBoxFee = orderBoxFee;
        }

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentGrade() {
            return commentGrade;
        }

        public void setCommentGrade(String commentGrade) {
            this.commentGrade = commentGrade;
        }

        public List<AllMealBean> getAllMeal() {
            return allMeal;
        }

        public void setAllMeal(List<AllMealBean> allMeal) {
            this.allMeal = allMeal;
        }

        public static class AllMealBean {
            /**
             * name : 无辣不欢组合
             * count : 1
             */

            private String name;
            private String count;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
