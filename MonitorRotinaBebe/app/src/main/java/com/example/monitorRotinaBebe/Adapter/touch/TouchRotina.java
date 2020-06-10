package com.example.monitorRotinaBebe.Adapter.touch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRotina;

public class TouchRotina extends ItemTouchHelper.SimpleCallback {


    private AdapterRotina adapterRotina;
    private  AppCompatActivity activity;

    public TouchRotina(AdapterRotina adapter, AppCompatActivity activity){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapterRotina = adapter;
        this.activity = activity;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
        adapterRotina.remover(viewHolder.getAdapterPosition());

    }


}
