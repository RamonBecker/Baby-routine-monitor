package com.example.monitorRotinaBebe.threads;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.ArrayList;
import java.util.List;

public class RetornarEventosRotina implements  Runnable{
    private AppDataBase bd;
    private static List<Rotina> rotinas = new ArrayList<>();
    private String evento;
    private String data;


    public RetornarEventosRotina(AppCompatActivity activity){
            bd = AppDataBase.getInstance(activity);
    }

    @Override
    public void run() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinas = bd.daoDataBase().getAllEvento(evento, data);
            }
        });
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static List<Rotina> getRotinas() {
        return rotinas;
    }
}
