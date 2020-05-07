package com.example.maskerin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.R;
import com.example.maskerin.class_object.Order;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    LayoutInflater inflater;
    ArrayList<Order> orders;

    public InfoAdapter(Context context, ArrayList<Order> orders){
        this.inflater = LayoutInflater.from(context);
        this.orders = orders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_info, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Order current = orders.get(position);
        holder.title.setText(current.title);
        holder.content.setText(current.orderData);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_name);
            content = itemView.findViewById(R.id.tv_title_content_name);
        }
    }
}
