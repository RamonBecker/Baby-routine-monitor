package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterFilme;


public class TouchFilme extends ItemTouchHelper.SimpleCallback {


    private AdapterFilme adapterFilme;

    public TouchFilme(AdapterFilme adapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapterFilme = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapterFilme.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterFilme.remover(viewHolder.getAdapterPosition());
    }
}
