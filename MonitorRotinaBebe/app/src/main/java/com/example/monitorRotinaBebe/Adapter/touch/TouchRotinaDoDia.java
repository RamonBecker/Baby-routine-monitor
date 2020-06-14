package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRotinaDoDia;

public class TouchRotinaDoDia extends ItemTouchHelper.SimpleCallback {


    private AdapterRotinaDoDia adapterRotinaDoDia;
    private AppCompatActivity activity;

    public TouchRotinaDoDia(AdapterRotinaDoDia adapter, AppCompatActivity activity) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapterRotinaDoDia = adapter;
        this.activity = activity;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapterRotinaDoDia.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterRotinaDoDia.remover(viewHolder.getAdapterPosition());

    }
}
