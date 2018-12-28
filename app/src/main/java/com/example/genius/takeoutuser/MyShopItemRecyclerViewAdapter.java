package com.example.genius.takeoutuser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

public class MyShopItemRecyclerViewAdapter extends RecyclerView.Adapter<MyShopItemRecyclerViewAdapter.ViewHolder> {
    Context context;
   List<AllShop.ShopArrBean> list;

    public MyShopItemRecyclerViewAdapter(Context context, List<AllShop.ShopArrBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shopitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.shopName.setText(list.get(position).getName());
        holder.shopAddress.setText(list.get(position).getAddress());
        holder.shopTime.setText(list.get(position).getTime());
        holder.shopSales.setText(list.get(position).getScales());
        holder.shopGrade.setText(list.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView shopName;
        TextView shopAddress;
        TextView shopTime;
        TextView shopSales;
        TextView shopGrade;
        LinearLayout shopItemLinerLayout;
        public ViewHolder(View view) {
            super(view);
            shopName = view.findViewById(R.id.shop_name);
            shopAddress = view.findViewById(R.id.shop_address);
            shopTime = view.findViewById(R.id.shop_time);
            shopSales = view.findViewById(R.id.shop_sales);
            shopGrade = view.findViewById(R.id.shop_grade);
            shopItemLinerLayout = view.findViewById(R.id.shop_item_liner_layout);
            shopItemLinerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,ShopMenuActivity.class).putExtra("id",list.get(getLayoutPosition()).getId()));
                }
            });

        }
    }
}
