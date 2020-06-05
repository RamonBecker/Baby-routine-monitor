package com.example.monitorRotinaBebe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.threads.RetornarRotinas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FragmentoRegistroEventoBebe extends Fragment {

    private Spinner spinner_eventos_bebe;
    private Button buttonRegistrarEvento;
    private DaoEventoBebe daoEventoBebe;
    private SimpleDateFormat horaFormatada;
    private SimpleDateFormat dataFormatada;
    public List<Rotina> rotinaList = new ArrayList<>();
    private AppDataBase bd;
    private RetornarRotinas retornarRotinas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        daoEventoBebe = new DaoEventoBebe((AppCompatActivity) getContext());

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
        retornarRotinas = new RetornarRotinas((AppCompatActivity) getContext());
        AppDataBase.databaseWriteExecutor.execute(retornarRotinas);
        rotinaList.addAll(retornarRotinas.getRotinas());
    }


    private void açãoBotaoRegistrarEvento(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evento = getSelectedItem(v);

                horaFormatada = new SimpleDateFormat("HH:mm");
                dataFormatada = new SimpleDateFormat("y:M:d");

                horaFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));

                Date hora_data_atual = Calendar.getInstance().getTime();
                String horaAtual = horaFormatada.format(hora_data_atual);
                String dataAtual = dataFormatada.format(hora_data_atual);

                Log.i("Lista", ""+retornarRotinas.getRotinas());

                Rotina ultimaRotina =  retornarRotinas.getRotinas().get(retornarRotinas.getRotinas().size()-1);
                
                if(ultimaRotina.getEvento().equalsIgnoreCase("Dormiu")){
                    daoEventoBebe.inserirRotina(new Rotina("Acordou",dataAtual,horaAtual,R.drawable.bebeacordando));
                }
                int idImagem = 0;

                if(evento.equalsIgnoreCase("Acordou")){
                    idImagem = R.drawable.bebeacordando;
                }
                else if(evento.equalsIgnoreCase("Dormindo")){
                    idImagem = R.drawable.bebedormindo;
                }
                else if(evento.equalsIgnoreCase("Trocou")){
                    idImagem = R.drawable.bebetrocou;
                }
                else if(evento.equalsIgnoreCase("Mamou")){
                    idImagem = R.drawable.bebemamou;
                }

                daoEventoBebe.inserirRotina(new Rotina(evento, dataAtual, horaAtual, idImagem));

                Toast.makeText(getContext(), "Evento cadastro com sucesso !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getSelectedItem(View view) {
        return (String) spinner_eventos_bebe.getSelectedItem();
    }
}
