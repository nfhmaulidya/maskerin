package com.example.maskerin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.OrderInfoActivity;
import com.example.maskerin.R;
import com.example.maskerin.class_object.History;
import com.example.maskerin.class_object.Order;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    LayoutInflater inflater;
    ArrayList<History> histories;
    Context context;

    public HistoryAdapter(Context context, ArrayList<History> histories){
        this.inflater = LayoutInflater.from(context);
        this.histories = histories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final History current = histories.get(position);
        holder.pharmacy.setText(current.pharmacy);
        holder.address.setText(current.address);
        holder.date.setText(current.date);
        holder.total.setText(current.total);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderInfoActivity.class);
//                intent.putExtra("pharmacy", current.pharmacy);
//                intent.putExtra("address", current.address);
//                intent.putExtra("date", current.date);
//                intent.putExtra("total", current.total);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pharmacy, address, date, total;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacy = itemView.findViewById(R.id.tv_pharmacy);
            address = itemView.findViewById(R.id.tv_address);
            date = itemView.findViewById(R.id.tv_date_transaction);
            total = itemView.findViewById(R.id.tv_total_mask);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
