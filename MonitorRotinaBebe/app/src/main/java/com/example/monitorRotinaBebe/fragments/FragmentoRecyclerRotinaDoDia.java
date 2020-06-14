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
import com.example.monitorRotinaBebe.Adapter.AdapterRotinaDoDia;
import com.example.monitorRotinaBebe.Adapter.touch.TouchRotinaDoDia;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FragmentoRecyclerRotinaDoDia extends Fragment {


    private RecyclerView recyclerView;
    private AdapterRotinaDoDia adapterRotinaDoDia;
    public static String REMOVER = "";
    private MainActivity activity;
    private MainActivity mainActivity;
    private Button botaoExcluir;
    private DaoEventoBebe daoEventoBebe;

    public FragmentoRecyclerRotinaDoDia() {
    }

    public FragmentoRecyclerRotinaDoDia(MainActivity activity) {
        this.activity = activity;
        this.daoEventoBebe = new DaoEventoBebe(activity);
        carregarDadosRotinaDia();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterRotinaDoDia = new AdapterRotinaDoDia((AppCompatActivity) getActivity());
        recyclerView.setAdapter(adapterRotinaDoDia);
        this.daoEventoBebe = new DaoEventoBebe(activity);
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchRotinaDoDia(adapterRotinaDoDia, activity));
        touchHelper.attachToRecyclerView(recyclerView);
        carregarDadosRotinaDia();
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
