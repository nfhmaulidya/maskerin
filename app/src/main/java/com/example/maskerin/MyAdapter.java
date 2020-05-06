package com.example.maskerin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String name[], distance[], address[], stock[], date[], time[], phone[];
    Context context;

    public MyAdapter(Context context, String[] name, String[] distance, String[] address,
                     String[] stock, String[] date, String[] time, String[] phone){
        this.context = context;
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.stock = stock;
        this.date = date;
        this.time = time;
        this.phone = phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pharmacy, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(this.name[position]);
        holder.distance.setText(this.distance[position]);
        holder.address.setText(this.address[position]);
        holder.stock.setText(this.stock[position]);
        holder.date.setText(this.date[position]);
        holder.time.setText(this.time[position]);
        holder.phone.setText(this.phone[position]);
    }

    @Override
    public int getItemCount() {
        return this.name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, distance, address, stock, date, time, phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            distance = itemView.findViewById(R.id.tv_distance);
            address = itemView.findViewById(R.id.tv_address);
            stock = itemView.findViewById(R.id.tv_stock);
            date = itemView.findViewById(R.id.tv_date);
            time = itemView.findViewById(R.id.tv_time);
            phone = itemView.findViewById(R.id.tv_phone);
        }
    }
}
