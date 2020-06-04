package com.example.monitorRotinaBebe.BD;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.entites.Rotina;

public class DaoEventoBebe{
    private AppDataBase bd;

    public DaoEventoBebe(AppCompatActivity activity) {
        bd = AppDataBase.getInstance(activity);
    }

    public void inserirRotina(final Rotina rotina){
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().inserirRotina(rotina);
            }
        });
    }


}
