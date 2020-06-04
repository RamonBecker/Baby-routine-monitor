package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterAtor;


public class TouchAtor extends ItemTouchHelper.SimpleCallback {


    private AdapterAtor adapterAtor;

    public TouchAtor(AdapterAtor adapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapterAtor = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapterAtor.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterAtor.remover(viewHolder.getAdapterPosition());
    }
}
