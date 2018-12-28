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

public class OrderFragment extends Fragment {
    private MyOrderRecyclerViewAdapter myOrderRecyclerViewAdapter;
    private List<OrderInfo.AllOrderBean> list;
    public OrderFragment() {
        list = new ArrayList<>();
        myOrderRecyclerViewAdapter = new MyOrderRecyclerViewAdapter(list);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.order_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myOrderRecyclerViewAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(container.getContext(),R.drawable.home_fragment_divider_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }
    public void updateData(List<OrderInfo.AllOrderBean>  tList){
        list.clear();
        list.addAll(tList);
        myOrderRecyclerViewAdapter.notifyDataSetChanged();
    }
}
