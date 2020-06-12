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
    private String dataAtual;
    private Date hora_data_atual;
    private SimpleDateFormat dataFormatada;

    public FragmentoRelatorioRotina() {

    }

    public FragmentoRelatorioRotina(AppCompatActivity activity) {

        this.activity = activity;
        daoEventoBebe = new DaoEventoBebe(activity);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_resumo_ultimos_dias, container, false);


        daoEventoBebe = new DaoEventoBebe(activity);
        dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        hora_data_atual = Calendar.getInstance().getTime();
        dataAtual = dataFormatada.format(hora_data_atual);
        // daoEventoBebe.setEvento("Acordou");
        //  daoEventoBebe.setData(dataAtual);
        daoEventoBebe.inicializarRelatorios();
        daoEventoBebe.n_vezes_Trocados_Mamou(dataAtual);

        Log.i("N trocados", "" + DaoEventoBebe.n_vezes_trocados);
        horas();
        return view;
    }

    private void horas() {


        for (String data : DaoEventoBebe.getDatas()) {
            daoEventoBebe.horasDormidas(data);
            daoEventoBebe.n_vezes_Trocados_Mamou(data);
        }


        Log.i("Relatorios list", "" + daoEventoBebe.getRelatorioRotinas());

    }
}
