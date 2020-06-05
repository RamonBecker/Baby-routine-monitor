package com.example.monitorRotinaBebe.BD;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.fragments.FragmentoRegistroEventoBebe;

import java.util.ArrayList;
import java.util.List;

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


    public void getAll(final List<Rotina> list){
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Rotina> rotinaListBD = bd.daoDataBase().getAll();

                for (Rotina r: rotinaListBD) {
                    list.add(r);
                }
                Log.i("Dentro do RUN1",""+list);
            }

        });
        Log.i("Fora do RUN",""+list);

    }

}
