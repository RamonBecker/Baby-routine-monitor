package com.example.monitorRotinaBebe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.Adapter.AdapterRotinaEvento;
import com.example.monitorRotinaBebe.Adapter.touch.TouchRotinaEvento;
import com.example.monitorRotinaBebe.R;

public class FragmentoRecyclerRotinaEvento extends Fragment {

    private AdapterRotinaEvento adapterRotinaEvento;
    private RecyclerView recyclerView;
    private AppCompatActivity activity;
    private String evento;


    public FragmentoRecyclerRotinaEvento(AppCompatActivity activity, String evento){
        this.activity = activity;
        this.evento = evento;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterRotinaEvento = new AdapterRotinaEvento(activity, evento);
        recyclerView.setAdapter(adapterRotinaEvento);


        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchRotinaEvento(adapterRotinaEvento,activity));
        touchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
}
