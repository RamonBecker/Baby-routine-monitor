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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRelatorio;
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
    private RecyclerView recyclerView;
    private AdapterRelatorio adapterRelatorio;

    public FragmentoRelatorioRotina() {

    }

    public FragmentoRelatorioRotina(AppCompatActivity activity) {

        this.activity = activity;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterRelatorio = new AdapterRelatorio(this.activity);
        recyclerView.setAdapter(adapterRelatorio);

        Log.i("N trocados", "" + DaoEventoBebe.n_vezes_trocados);
        return view;
    }

}
