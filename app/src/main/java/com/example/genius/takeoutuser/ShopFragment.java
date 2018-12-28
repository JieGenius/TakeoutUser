package com.example.genius.takeoutuser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {
    private List<AllShop.ShopArrBean> list;
    private MyShopItemRecyclerViewAdapter adapter;
    public ShopFragment() {
        list = new ArrayList<>();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopitem_list, container, false);
        adapter = new MyShopItemRecyclerViewAdapter(getActivity(),list);
        RecyclerView recyclerView = view.findViewById(R.id.shop_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    public void updateData(List<AllShop.ShopArrBean> tList){
        if(list!=null&&adapter!=null){
            list.clear();
            list.addAll(tList);
            adapter.notifyDataSetChanged();
        }

    }

}
