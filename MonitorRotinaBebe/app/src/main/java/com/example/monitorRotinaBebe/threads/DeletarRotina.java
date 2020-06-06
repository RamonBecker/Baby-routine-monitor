package com.example.monitorRotinaBebe.threads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Delete;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.entites.Rotina;

public class DeletarRotina implements  Runnable {
    private Rotina rotina;
    private AppDataBase bd;

    public DeletarRotina(Rotina rotina,  AppCompatActivity activity){
        this.rotina = rotina;
        bd = AppDataBase.getInstance(activity);
    }


    @Override
    public void run() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().deletarRotina(rotina);
            }
        });
    }
}
