package com.example.monitorRotinaBebe.threads;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.BD.AppDataBase;

public class DeletarTodasRotinas implements  Runnable {
    private AppDataBase bd;

    public DeletarTodasRotinas( AppCompatActivity activity) {
        this.bd = AppDataBase.getInstance(activity);
    }

    @Override
    public void run() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().deleteAll();
            }
        });
    }
}
