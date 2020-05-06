package com.example.maskerin.nav_ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.LoginActivity;
import com.example.maskerin.MapsActivity;
import com.example.maskerin.MyAdapter;
import com.example.maskerin.R;

public class PharmacyFragment extends Fragment {

    private PharmacyViewModel dashboardViewModel;
    public Button button = null;
    Intent intent;

    RecyclerView recyclerView;
    String name[], distance[], address[], stock[], date[], time[], phone[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(PharmacyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        final TextView textView = root.findViewById(R.id.tv_pharmacy);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //button onclick listener
        intent = new Intent(getActivity(), MapsActivity.class);
        button = (Button) root.findViewById(R.id.bt_see_maps);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //reciclerview
        recyclerView = root.findViewById(R.id.rv_list_of_pharmacy);

        //setting datas
        name = getResources().getStringArray(R.array.pharmacy_name);
        distance = getResources().getStringArray(R.array.pharmacy_distance);
        address = getResources().getStringArray(R.array.pharmacy_address);
        stock = getResources().getStringArray(R.array.pharmacy_stock);
        date = getResources().getStringArray(R.array.pharmacy_date);
        time = getResources().getStringArray(R.array.pharmacy_time);
        phone = getResources().getStringArray(R.array.pharmacy_phone);

        //creating object adapter
        MyAdapter myAdapter = new MyAdapter(getActivity(), name, distance, address, stock, date,
                time, phone);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.app_bar_search){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
