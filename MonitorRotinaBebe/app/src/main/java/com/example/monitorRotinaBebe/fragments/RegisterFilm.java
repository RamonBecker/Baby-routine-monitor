package com.example.monitorRotinaBebe.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.controller.ControllerAtor;
import com.example.monitorRotinaBebe.controller.ControllerDiretor;
import com.example.monitorRotinaBebe.controller.ControllerFilme;
import com.example.monitorRotinaBebe.entites.Ator;
import com.example.monitorRotinaBebe.entites.Diretor;
import com.example.monitorRotinaBebe.entites.Filme;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterFilm extends Fragment {


    private Button buttonRegisterFilm;
    private EditText nomeFilme;
    private EditText generoFilme;
    private EditText anoFilme;
    private Spinner spinnerAtor;
    private Spinner spinnerDiretor;
    private MainActivity activity;
    private ControllerAtor controllerAtor;
    private ControllerDiretor controllerDiretor;
    private ControllerFilme controllerFilme;
    private ArrayAdapter<String>  dataAdapterAtor;
    private ArrayAdapter<String>  dataAdapterDiretor;
    private List<String> listAtores;
    private List<String> listDiretores;
    private DatePickerDialog picker;
    private Ator ator;
    private Diretor diretor;
    private String itemSelected;
    private FragmentTransaction fragmentTransaction;

    public RegisterFilm(MainActivity activity){
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_film, container, false);


        controllerAtor = ControllerAtor.getInstance();
        controllerDiretor = ControllerDiretor.getInstance();
        controllerFilme = ControllerFilme.getInstance();

        popularLists();

        nomeFilme = view.findViewById(R.id.editTextRegisterNameFilm);
        generoFilme = view.findViewById(R.id.editTextRegisterGenero);
        anoFilme = view.findViewById(R.id.editTextRegisterDate);
        buttonRegisterFilm = view.findViewById(R.id.buttonRegisterFilm);
        spinnerAtor = view.findViewById(R.id.spinnerAtor);
        spinnerDiretor = view.findViewById(R.id.spinnerDiretor);

        dataAdapterAtor = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        dataAdapterAtor.add("Escolhe a sua opção");
        dataAdapterAtor.addAll(listAtores);
        dataAdapterAtor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapterDiretor = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item );
        dataAdapterDiretor.add("Escolha a sua opção");
        dataAdapterDiretor.addAll(listDiretores);
        dataAdapterDiretor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAtor.setAdapter(dataAdapterAtor);
        spinnerDiretor.setAdapter(dataAdapterDiretor);
        actionSpinner(spinnerAtor);
        actionSpinner(spinnerDiretor);

        actionCalender(anoFilme);
        actionButton(buttonRegisterFilm);

        return view;
    }

    private void popularLists(){
        listAtores = new ArrayList<>();
        listDiretores = new ArrayList<>();

        for (Ator ator: controllerAtor.getListAtor()) {
              listAtores.add(ator.getNome());
        }

        for (Diretor diretor: controllerDiretor.getListDiretor()) {
            listDiretores.add(diretor.getNome());
        }
    }

    private void actionSpinner(final Spinner spinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escolha a sua opção")){
                }
                else{

                    if(spinner.getId() == R.id.spinnerDiretor){
                        itemSelected = parent.getItemAtPosition(position).toString();
                        buscarDiretor();
                    }

                    if(spinner.getId() == R.id.spinnerAtor){
                       // Toast.makeText(parent.getContext(), "Ator", Toast.LENGTH_LONG).show();
                        itemSelected = parent.getItemAtPosition(position).toString();

                        buscarAtor();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buscarAtor(){

        ator = controllerAtor.buscarAtor(itemSelected);

        if(ator != null) {
            Log.i("Ator", "" + ator.toString());
        }
    }

    private void buscarDiretor(){
         diretor = controllerDiretor.buscarDiretor(itemSelected);

         if(diretor != null) {
             Log.i("Diretor", "" + diretor.toString());
         }
    }

    private void actionCalender(EditText date){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                anoFilme.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    private void actionButton(Button button){


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nome = String.valueOf(nomeFilme.getText());
                    String genero = String.valueOf(generoFilme.getText());
                    String ano = String.valueOf(anoFilme.getText());
                    Filme filme = new Filme(nome, genero, ano, R.drawable.filme );
                    filme.setAtor(ator);
                    filme.setDiretor(diretor);
                    controllerFilme.getListaFilme().add(filme);

                    Toast.makeText(getContext(), "Filme cadastrado com sucesso !", Toast.LENGTH_LONG).show();
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_fragment, new RecyclerFragmentFilme(activity));

                    Thread.sleep(100);

                    fragmentTransaction.commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void clear(){
        nomeFilme.setText("");
        generoFilme.setText("");
        anoFilme.setText("");
    }
}
