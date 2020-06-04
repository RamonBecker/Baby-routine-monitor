package com.example.monitorRotinaBebe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.Adapter.AdapterAtor;
import com.example.monitorRotinaBebe.Adapter.touch.TouchAtor;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.controller.ControllerAtor;

public class RecyclerFragmentAtor extends Fragment {

    private RecyclerView recyclerView;
    private ControllerAtor controllerAtor;
    private AdapterAtor adapterAtor;
    private MainActivity activity;

    public RecyclerFragmentAtor(MainActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterAtor = new AdapterAtor(activity);
        recyclerView.setAdapter(adapterAtor);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchAtor(adapterAtor));
        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }
}
