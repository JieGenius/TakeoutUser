package com.example.genius.takeoutuser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ShopMenuAdapter extends RecyclerView.Adapter<ShopMenuAdapter.ViewHolder> {
    private Map<Integer,Integer> result;
    private static final String TAG = "ShopMenuAdapter";
    List<OneShopMenu.MenuBean> list;
    Context context;

    public ShopMenuAdapter(List<OneShopMenu.MenuBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.result = new HashMap<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_menu_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.shopMenuName.setText(list.get(i).getName());
        viewHolder.shopMenuIntro.setText(list.get(i).getIntroduce());
        viewHolder.shopMenuPrice.setText(list.get(i).getPrice()+"X"+list.get(i).getDiscount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public Map<Integer,Integer> getData(){
        return result;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shopMenuName;
        TextView shopMenuIntro;
        TextView shopMenuPrice;
        ImageButton shopMenuRemove;
        TextView shopMenuCount;
        ImageButton shopMenuAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopMenuName = itemView.findViewById(R.id.shop_menu_name);
            shopMenuIntro = itemView.findViewById(R.id.shop_menu_intro);
            shopMenuPrice = itemView.findViewById(R.id.shop_menu_price);
            shopMenuRemove = itemView.findViewById(R.id.shop_menu_remove);
            shopMenuCount = itemView.findViewById(R.id.shop_menu_count);
            shopMenuAdd = itemView.findViewById(R.id.shop_menu_add);
            shopMenuRemove.setOnClickListener(this);
            shopMenuAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int count = Integer.parseInt(shopMenuCount.getText().toString());
            switch (v.getId()){
                case R.id.shop_menu_remove:
                    if(count>0){
                        shopMenuCount.setText(String.valueOf(count-1));
                        result.put(getLayoutPosition(),count-1);
                    }
                    else {
                        Toast.makeText(context,"数量不能低于0个",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.shop_menu_add:
                    if(count<99){
                        shopMenuCount.setText(String.valueOf(count+1));
                        result.put(getLayoutPosition(),count+1);
                    }
                    else{
                        Toast.makeText(context,"最多只能选择99个",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
