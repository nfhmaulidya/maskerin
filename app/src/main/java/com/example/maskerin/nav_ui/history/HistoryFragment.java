package com.example.maskerin.nav_ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskerin.R;
import com.example.maskerin.adapter.HistoryAdapter;
import com.example.maskerin.class_object.History;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        root = inflater.inflate(R.layout.fragment_history, container, false);
        init();
        return root;
    }

    public void init(){
        ArrayList<History> histories = new ArrayList<History>();
        histories.add(new History("Apotek Bebas Nama", "Jl. Pokok e super bebas wes emboh", "28-04-20", "10"));
        histories.add(new History("Apotek Aman Damai Sentosa", "Jl. Pokok e super bebas wes emboh iki alamat dowo cuy", "18-04-20", "5"));
        histories.add(new History("Apotek Jaya Selalu", "Jl. Pokok e super bebas wes emboh iki alamat dowo cuy ehehehe", "08-04-20", "5"));

        recyclerView = root.findViewById(R.id.rv_order_history);
        HistoryAdapter myAdapter = new HistoryAdapter(getActivity(), histories);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
