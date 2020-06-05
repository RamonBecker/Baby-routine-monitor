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
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.Adapter.AdapterRotina;
import com.example.monitorRotinaBebe.R;

public class FragmentoRecyclerRotinaDoDia extends Fragment {


    private RecyclerView recyclerView;
    private AdapterRotina adapterRotina;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterRotina = new AdapterRotina((AppCompatActivity) getActivity());
        recyclerView.setAdapter(adapterRotina);
        return view;
    }
}
