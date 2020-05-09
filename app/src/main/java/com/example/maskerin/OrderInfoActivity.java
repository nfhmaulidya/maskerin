package com.example.maskerin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.maskerin.adapter.InfoAdapter;
import com.example.maskerin.class_object.Order;

import java.util.ArrayList;

public class OrderInfoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        init();
    }

    public void init(){
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("Nama", "Mr. Bean"));
        orders.add(new Order("NIK", "1234567890"));
        orders.add(new Order("Tanggal Pesanan", "01-05-20"));
        orders.add(new Order("Jenis Masker", "Dewasa"));
        orders.add(new Order("Jumlah yang Dibeli", "1000 buah"));
        orders.add(new Order("Harga Satuan", "100000"));
        orders.add(new Order("Total", "100000000"));

        recyclerView = findViewById(R.id.rv_order_info);
        InfoAdapter myAdapter = new InfoAdapter(this, orders);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // buat get kiriman dr fragment history
//    public void getData(){
//        if (getIntent().hasExtra("pharmacy") && getIntent().hasExtra("address") && getIntent().hasExtra("date")
//        && getIntent().hasExtra("total")){
//
//        } else {
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
//        }
//    }
}
