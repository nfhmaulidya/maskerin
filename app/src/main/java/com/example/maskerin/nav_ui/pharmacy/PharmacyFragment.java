package com.example.maskerin.nav_ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
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

import java.util.ArrayList;

public class PharmacyFragment extends Fragment {

    private PharmacyViewModel pharmacyViewModel;
    //NestedScrollView scrollView;
    public Button button;
    Intent intent;
    View root;

    RecyclerView recyclerView;
    boolean check_ScrollingUp = false;

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

        //nestedScrollView = root.findViewById(R.id.nested_scroll_view);

        init();
        scrollListener();
        return root;
    }

    public void init(){
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacy("Apotek Sehat", "845m", "Jl. Sehat selalu setiap hari tanpa ada halangan",
                "Sisa < 50", "02-05-20", "15:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Jaya Sentosa", "75m", "Jl. Jaya sentosa sepanjang masa",
                "Habis", "01-05-20", "23:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Abadi Abada", "1025m", "Jl. Abadi selamanya",
                "Sisa > 100", "05-05-20", "13:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Mawar Melati Semuanya Indah", "327m", "Jl. Mawar adalah nama bunga yang wanginya kebangetan wes pokok",
                "Sisa > 100", "02-05-20", "03:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Lihat Kebunku", "2649m", "Jl. Lihat Kebunku penuh dengan bunga",
                "Sisa < 50", "01-05-20", "09:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Darah Muda", "100m", "Jl. Darahnya Para Remaja Uo Uooooo",
                "Habis", "07-05-20", "05:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Senyum Nyaman", "1963m", "Jl. Senyum nyaman senyaman kasur pas lagi capek-capeknya",
                "Habis", "05-05-20", "10:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Darah Muda", "100m", "Jl. Darahnya Para Remaja Uo Uooooo",
                "Habis", "07-05-20", "05:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Darah Muda", "100m", "Jl. Darahnya Para Remaja Uo Uooooo",
                "Habis", "07-05-20", "05:00", "085123456789"));
        pharmacies.add(new Pharmacy("Apotek Darah Muda", "100m", "Jl. Darahnya Para Remaja Uo Uooooo",
                "Habis", "07-05-20", "05:00", "085123456789"));

        recyclerView = root.findViewById(R.id.rv_list_of_pharmacy);
        PharmacyAdapter myAdapter = new PharmacyAdapter(getActivity(), pharmacies);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void scrollListener(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    if(check_ScrollingUp){
                        button.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.trans_downwards));
                        check_ScrollingUp = false;
                    }
                } else {
                    if(!check_ScrollingUp){
                        button.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.trans_upwards));
                        check_ScrollingUp = true;
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY > oldScrollY) {
//                    if (check_ScrollingUp) {
//                        //button.setVisibility(View.GONE);
//                        button.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.trans_downwards));
//                        check_ScrollingUp = false;
//                    }
//                } else {
//                    if (!check_ScrollingUp) {
//                        //button.setVisibility(View.VISIBLE);
//                        button.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.trans_upwards));
//                        check_ScrollingUp = true;
//                    }
//                }
//            }
//        });
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
