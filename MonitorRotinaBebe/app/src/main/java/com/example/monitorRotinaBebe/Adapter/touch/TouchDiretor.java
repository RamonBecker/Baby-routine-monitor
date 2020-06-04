package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.Adapter.AdapterDiretor;


public class TouchDiretor extends ItemTouchHelper.SimpleCallback {


    private AdapterDiretor adapterDiretor;

    public TouchDiretor(AdapterDiretor adapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapterDiretor = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapterDiretor.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterDiretor.remover(viewHolder.getAdapterPosition());
    }
}
