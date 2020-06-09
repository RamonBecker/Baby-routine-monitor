package com.example.monitorRotinaBebe.BD;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.fragments.FragmentoRegistroEventoBebe;

import java.util.ArrayList;
import java.util.List;

public class DaoEventoBebe{
    private AppDataBase bd;
    private String data;
    private Rotina rotina;
    private static List<Rotina> rotinas = new ArrayList<>();

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

    public void getRotinaDoDia(){

        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinas = bd.daoDataBase().getAllDate(data);
                Log.i("Rotina dia",""+rotinas);
            }
        });
    }

    public void removerRotina(){
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().deletarRotina(rotina);
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

    public void atualizarRotina(){
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().updateRotina(rotina);
            }
        });
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public static List<Rotina> getRotinas() {
        return rotinas;
    }
}
