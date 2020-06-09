package com.example.monitorRotinaBebe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRotina;
import com.example.monitorRotinaBebe.Adapter.touch.TouchRotina;
import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.threads.RetornarRotinas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FragmentoRecyclerRotinaDoDia extends Fragment {


    private RecyclerView recyclerView;
    private AdapterRotina adapterRotina;
    private RetornarRotinas retornarRotinas;
    public static String REMOVER = "";
    private MainActivity activity;
    private MainActivity mainActivity;
    private Button botaoExcluir;
    private DaoEventoBebe daoEventoBebe;

    public FragmentoRecyclerRotinaDoDia() {
    }

    public FragmentoRecyclerRotinaDoDia(MainActivity activity) {
        this.activity = activity;
        daoEventoBebe = new DaoEventoBebe(activity);
    //    retornarRotinaDia = new RetornarRotinaDia(activity);
        retornarRotinas = new RetornarRotinas(activity);
      //  AppDataBase.databaseWriteExecutor.execute(retornarRotinaDia);
        AppDataBase.databaseWriteExecutor.execute(retornarRotinas);
        carregarDadosRotinaDia();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterRotina = new AdapterRotina((AppCompatActivity) getActivity());
        recyclerView.setAdapter(adapterRotina);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchRotina(adapterRotina, activity));
        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }
    private void carregarDadosRotinaDia() {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        daoEventoBebe.setData(dataAtual);
        daoEventoBebe.getRotinaDoDia();

    }



}
