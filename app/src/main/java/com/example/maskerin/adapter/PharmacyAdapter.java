package com.example.maskerin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.R;
import com.example.maskerin.class_object.Pharmacy;

import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.MyViewHolder> {

    LayoutInflater inflater;
    ArrayList<Pharmacy> pharmacies;

    public PharmacyAdapter(Context context, ArrayList<Pharmacy> pharmacies){
        this.inflater = LayoutInflater.from(context);
        this.pharmacies = pharmacies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_pharmacy, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Pharmacy current = pharmacies.get(position);
        holder.name.setText(current.name);
        holder.distance.setText(current.distance);
        holder.address.setText(current.address);
        holder.stock.setText(current.stock);
        holder.date.setText(current.dateUpdate);
        holder.time.setText(current.timeUpdate);
        holder.phone.setText(current.phone);
    }

    @Override
    public int getItemCount() {
        return this.pharmacies.size();
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
