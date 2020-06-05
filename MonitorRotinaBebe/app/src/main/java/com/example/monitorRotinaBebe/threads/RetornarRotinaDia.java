package com.example.monitorRotinaBebe.threads;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.ArrayList;
import java.util.List;

public class RetornarRotinaDia implements  Runnable {
    public List<Rotina> rotinas = new ArrayList<>();
    private String data;
    private AppDataBase bd;

    public RetornarRotinaDia(AppCompatActivity activity){
        bd = AppDataBase.getInstance(activity);
    }
    @Override
    public void run() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinas = bd.daoDataBase().getAllDate(data);
                Log.i("Rotina dia",""+rotinas);
            }
        });
    }

    public List<Rotina> getRotinas() {
        return rotinas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
