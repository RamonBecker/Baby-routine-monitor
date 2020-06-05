package com.example.monitorRotinaBebe.threads;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.fragments.FragmentoRegistroEventoBebe;

import java.util.ArrayList;
import java.util.List;

public class RetornarRotinas implements  Runnable {
    public  List<Rotina> rotinas = new ArrayList<>();
    private AppDataBase bd;

    public RetornarRotinas(AppCompatActivity activity) {
        this.bd = AppDataBase.getInstance(activity);;
    }

    @Override
    public void run() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinas= bd.daoDataBase().getAll();

                Log.i("Dentro do RUN1",""+rotinas);
               // FragmentoRegistroEventoBebe.rotinaList.addAll(rotinas);
            }

        });
    }

    public  List<Rotina> getRotinas() {
        return rotinas;
    }
}
