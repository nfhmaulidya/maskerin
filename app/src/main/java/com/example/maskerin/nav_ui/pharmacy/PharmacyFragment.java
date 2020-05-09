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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.LoginActivity;
import com.example.maskerin.MapsActivity;
import com.example.maskerin.adapter.PharmacyAdapter;
import com.example.maskerin.R;
import com.example.maskerin.class_object.Pharmacy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PharmacyFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;
    private ArrayList<Pharmacy> dataApotik;
    private Button pesan ;
    private FirebaseDatabase auth;


    private PharmacyViewModel pharmacyViewModel;
    public Button button = null;
    Intent intent;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pharmacyViewModel =
                ViewModelProviders.of(this).get(PharmacyViewModel.class);
        root = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        final TextView textView = root.findViewById(R.id.tv_pharmacy);
        pharmacyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        GetData();
        return root;
    }

    private void GetData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Apotik").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataApotik = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    Pharmacy apotik = snapshot.getValue(Pharmacy.class);
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    apotik.setKey(dataSnapshot.getKey());
                    dataApotik.add(apotik);


                }
                recyclerView = root.findViewById(R.id.rv_list_of_pharmacy);
                adapter = new PharmacyAdapter(dataApotik, getActivity());
                //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
