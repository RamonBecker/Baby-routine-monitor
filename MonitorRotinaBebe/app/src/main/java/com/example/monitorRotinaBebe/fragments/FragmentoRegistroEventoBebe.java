package com.example.monitorRotinaBebe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FragmentoRegistroEventoBebe extends Fragment {

    private Spinner spinner_eventos_bebe;
    private Button buttonRegistrarEvento;
    private DaoEventoBebe daoEventoBebe;
    private SimpleDateFormat horaFormatada;
    public List<Rotina> rotinaList = new ArrayList<>();
    private AppDataBase bd;
   // private RetornarRotinas retornarRotinas;
    private Date hora_data_atual;
    private AppCompatActivity activity;

    public FragmentoRegistroEventoBebe(){
    }


    public FragmentoRegistroEventoBebe(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoEventoBebe = new DaoEventoBebe(activity);
        carregarRotinas();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registro_evento_bebe, container, false);
        rotinaList = new ArrayList<>();
        spinner_eventos_bebe = view.findViewById(R.id.spinner_eventos_bebe);
        buttonRegistrarEvento = view.findViewById(R.id.buttonRegistrarEventoBebe);
        popularSpinner(spinner_eventos_bebe, getContext());
        açãoBotaoRegistrarEvento(buttonRegistrarEvento);



        return view;
    }

    private void popularSpinner(Spinner spinner, Context context) {
        List<String> eventos_bebe = new ArrayList<>();
        eventos_bebe.add("Acordou");
        eventos_bebe.add("Mamou");
        eventos_bebe.add("Trocou");
        eventos_bebe.add("Dormiu");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, eventos_bebe);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void carregarRotinas() {
        daoEventoBebe.setData(retornarDataFormatada());
        daoEventoBebe.getRotinaDoDia();
    }

    private String retornarDataFormatada(){
        SimpleDateFormat dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        return  dataAtual;
    }

    private void açãoBotaoRegistrarEvento(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evento = getSelectedItem(v);

                SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm");
                SimpleDateFormat dataFormatada = new SimpleDateFormat("y:M:d");
                dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
                horaFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));

                Date hora_data_atual = Calendar.getInstance().getTime();

                String horaAtual = horaFormatada.format(hora_data_atual);
                String dataAtual = retornarDataFormatada();

                Rotina ultimaRotina = null;
                if (!DaoEventoBebe.getRotinasdoDia().isEmpty()) {
                    ultimaRotina = DaoEventoBebe.getRotinasdoDia().get(DaoEventoBebe.getRotinasdoDia().size() - 1);
                }

                if (ultimaRotina != null) {
                    if(ultimaRotina.getEvento().equalsIgnoreCase("Dormiu") && evento.equalsIgnoreCase("Dormiu")){
                        Toast.makeText(getContext(), "Não foi possível cadastrar o evento, pois o bebê já esta dormindo", Toast.LENGTH_SHORT).show();
                        finalizarRegistroEvento();
                        return;
                    }

                    if (ultimaRotina.getEvento().equalsIgnoreCase("Dormiu") && !evento.equalsIgnoreCase("Acordou")) {
                        daoEventoBebe.inserirRotina(new Rotina("Acordou", dataAtual, horaAtual, R.drawable.bebeacordando));
                      //  Toast.makeText(getContext(), "Evento cadastro com sucesso !", Toast.LENGTH_SHORT).show();
                    //    finalizarRegistroEvento();
                     //   return;
                    }
                }
                int idImagem = 0;

                if (evento.equalsIgnoreCase("Acordou")) {
                    idImagem = R.drawable.bebeacordando;
                } else if (evento.equalsIgnoreCase("Dormiu")) {
                    idImagem = R.drawable.bebedormindo;
                } else if (evento.equalsIgnoreCase("Trocou")) {
                    idImagem = R.drawable.bebetrocou;
                } else if (evento.equalsIgnoreCase("Mamou")) {
                    idImagem = R.drawable.bebemamou;
                }

                daoEventoBebe.inserirRotina(new Rotina(evento, dataAtual, horaAtual, idImagem));
                Toast.makeText(getContext(), "Evento cadastro com sucesso !", Toast.LENGTH_SHORT).show();
                finalizarRegistroEvento();

            }
        });
    }

    private void finalizarRegistroEvento(){
        carregarRotinas();
        initializeFragment();
    }

    private void initializeFragment(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, new FragmentoRecyclerRotinaDoDia());
        fragmentTransaction.commit();
    }


    public String getSelectedItem(View view) {
        return (String) spinner_eventos_bebe.getSelectedItem();
    }
}
