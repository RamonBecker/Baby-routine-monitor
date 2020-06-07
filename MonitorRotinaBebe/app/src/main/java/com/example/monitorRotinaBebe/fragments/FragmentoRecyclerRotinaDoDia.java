package com.example.monitorRotinaBebe.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.Adapter.AdapterRotina;
import com.example.monitorRotinaBebe.Adapter.touch.TouchRotina;
import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.threads.AtualizarRotina;
import com.example.monitorRotinaBebe.threads.RetornarRotinaDia;

public class FragmentoRecyclerRotinaDoDia extends Fragment {


    private RecyclerView recyclerView;
    private AdapterRotina adapterRotina;
    private RetornarRotinaDia retornarRotinaDia;
    public static String REMOVER = "";
    private MainActivity activity;
    private MainActivity mainActivity;
    private Button botaoExcluir;

    public FragmentoRecyclerRotinaDoDia() {
    }

    public FragmentoRecyclerRotinaDoDia(MainActivity activity) {
        this.activity = activity;
        retornarRotinaDia = new RetornarRotinaDia(activity);
        AppDataBase.databaseWriteExecutor.execute(retornarRotinaDia);
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

   //     botaoExcluir = view.findViewById(R.id.buttonExcluirRotina);

        //activity.setAdapterRotina(adapterRotina);
    //    botaoconfirmarAcao(botaoExcluir);
        return view;
    }



}
