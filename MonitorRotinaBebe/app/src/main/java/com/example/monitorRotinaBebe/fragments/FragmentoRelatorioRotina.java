package com.example.monitorRotinaBebe.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FragmentoRelatorioRotina extends Fragment {

    private AppCompatActivity activity;
    private DaoEventoBebe daoEventoBebe;

    public FragmentoRelatorioRotina(){

    }
    public FragmentoRelatorioRotina(AppCompatActivity activity){

        this.activity = activity;
        daoEventoBebe = new DaoEventoBebe(activity);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_resumo_ultimos_dias, container, false);
        daoEventoBebe = new DaoEventoBebe(activity);
        SimpleDateFormat dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        daoEventoBebe.setEvento("Acordou");
        daoEventoBebe.setData(dataAtual);
     //   daoEventoBebe.contarEventos();
     //   daoEventoBebe.getAllDatas();
      //  daoEventoBebe.getAll();
        horas();
        return  view;
    }
    private void horas(){
        for (String data: DaoEventoBebe.getDatas()) {
                daoEventoBebe.horasDormidas(data);
        }
     //   Log.i("Relatorios list",""+DaoEventoBebe.getRelatorioRotinas());
     //   Log.i("Relatorios list",""+DaoEventoBebe.getDatas());
    }
}
