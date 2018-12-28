package com.example.genius.takeoutuser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PopWindowRecyclerViewAdapter extends RecyclerView.Adapter<PopWindowRecyclerViewAdapter.ViewHolder> {
    private List<SubmitMenuItem> list;

    public PopWindowRecyclerViewAdapter(List<SubmitMenuItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pop_window_recycler_view_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).name);
        viewHolder.price.setText(list.get(i).price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pop_window_menu_name);
            price = itemView.findViewById(R.id.pop_window_menu_price);
        }
    }
}
