package com.example.genius.takeoutuser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder> {
    List<OrderInfo.AllOrderBean> list;

    public MyOrderRecyclerViewAdapter(List<OrderInfo.AllOrderBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.orderInfoNum.setText(list.get(i).getOrderNum());
        viewHolder.orderInfoItemTime.setText(list.get(i).getOrderTime());
        StringBuilder stringBuilder = new StringBuilder();
        for(int j=0;j<list.get(i).getAllMeal().size();j++){
            stringBuilder.append(list.get(i).getAllMeal().get(j).getName());
            stringBuilder.append("X");
            stringBuilder.append(list.get(i).getAllMeal().get(j).getCount());
            if(j!=list.get(i).getAllMeal().size()-1){
                stringBuilder.append("\n");
            }
        }
        viewHolder.orderInfoItemOrderItem.setText(stringBuilder.toString());
        viewHolder.orderInfoItemDeliveryFee.setText(list.get(i).getOrderDeliveryFee());
        viewHolder.orderInfoItemBoxFee.setText(list.get(i).getOrderBoxFee());
        viewHolder.orderInfoItemSumFee.setText(list.get(i).getOrderSum());
        viewHolder.orderInfoItemDeliveryPhone.setText(list.get(i).getDeliveryPhone());
        viewHolder.orderInfoItemShopName.setText(list.get(i).getShopname());
        viewHolder.orderInfoItemShopAddress.setText(list.get(i).getShopAddress());
        viewHolder.orderInfoItemShopPhone.setText(list.get(i).getShopPhone());
        viewHolder.orderInfoItemComment.setText(list.get(i).getCommentContent());
        viewHolder.orderInfoItemGrade.setText(list.get(i).getCommentGrade());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderInfoNum;
        TextView orderInfoItemTime;
        TextView orderInfoItemOrderItem;
        TextView orderInfoItemDeliveryFee;
        TextView orderInfoItemBoxFee;
        TextView orderInfoItemSumFee;
        TextView orderInfoItemDeliveryPhone;
        TextView orderInfoItemShopPhone;
        TextView orderInfoItemShopAddress;
        TextView orderInfoItemShopName;
        TextView orderInfoItemComment;
        TextView orderInfoItemGrade;
        TextView orderInfoItemCommentTime;

        public ViewHolder(View view) {
            super(view);
            orderInfoNum = itemView.findViewById(R.id.order_info_num);
            orderInfoItemTime = itemView.findViewById(R.id.order_info_item_time);
            orderInfoItemOrderItem = itemView.findViewById(R.id.order_info_item_order_item);
            orderInfoItemDeliveryFee = itemView.findViewById(R.id.order_info_item_delivery_fee);
            orderInfoItemBoxFee = itemView.findViewById(R.id.order_info_item_box_fee);
            orderInfoItemSumFee = itemView.findViewById(R.id.order_info_item_sum_fee);
            orderInfoItemDeliveryPhone = itemView.findViewById(R.id.order_info_item_delivery_phone);
            orderInfoItemShopPhone = itemView.findViewById(R.id.order_info_item_shop_phone);
            orderInfoItemShopAddress = itemView.findViewById(R.id.order_info_shop_address);
            orderInfoItemShopName = itemView.findViewById(R.id.order_info_shop_name);
            orderInfoItemComment = itemView.findViewById(R.id.order_info_item_comment);
            orderInfoItemGrade = itemView.findViewById(R.id.order_info_item_grade);
            orderInfoItemCommentTime = itemView.findViewById(R.id.order_info_item_comment_time);
        }
    }
}
