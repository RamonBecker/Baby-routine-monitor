package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRotinaEvento;

public class TouchRotinaEvento extends ItemTouchHelper.SimpleCallback {


    private AppCompatActivity activity;
    private AdapterRotinaEvento adapterRotinaEvento;

    public TouchRotinaEvento(AdapterRotinaEvento adapterRotinaEvento, AppCompatActivity activity) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.activity = activity;
        this.adapterRotinaEvento = adapterRotinaEvento;

    }

    public TouchRotinaEvento(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapterRotinaEvento.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapterRotinaEvento.remover(viewHolder.getAdapterPosition());
    }
}
