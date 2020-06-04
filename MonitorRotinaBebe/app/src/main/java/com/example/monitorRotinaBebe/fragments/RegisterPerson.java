package com.example.monitorRotinaBebe.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.controller.ControllerAtor;
import com.example.monitorRotinaBebe.controller.ControllerDiretor;
import com.example.monitorRotinaBebe.entites.Ator;
import com.example.monitorRotinaBebe.entites.Diretor;

import java.util.Calendar;

public class RegisterPerson extends Fragment {
    private TextView titulo;
    private EditText namePerson;
    private EditText dataNascimentoPerson;
    private Button buttonRegister;
    public static String typeRegister = "";
    private ControllerDiretor controllerDiretor;
    private ControllerAtor controllerAtor;
    private DatePickerDialog picker;
    private TimePickerDialog pickerTime;
    private String nomeText;
    private  String dataNascimentoText;
    private FragmentTransaction fragmentTransaction;
    private MainActivity activity;
    private EditText editTextHora;

    public RegisterPerson(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_person, container, false);

        namePerson = view.findViewById(R.id.editTextNamePerson);
        dataNascimentoPerson = view.findViewById(R.id.editTextAnoNascimento);
        titulo = view.findViewById(R.id.textViewTituloPerson);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        controllerAtor = ControllerAtor.getInstance();
        controllerDiretor = ControllerDiretor.getInstance();

        actionButton(buttonRegister);
        actionCalender(dataNascimentoPerson);
        titulo.setText(typeRegister);

        return view;
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
                                dataNascimentoPerson.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    private void actionHora(EditText hora){
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void actionButton(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String aux = "";
                    nomeText =  String.valueOf(namePerson.getText());
                    dataNascimentoText = String.valueOf(dataNascimentoPerson.getText());

                    fragmentTransaction = getFragmentManager().beginTransaction();

                    if(typeRegister.contentEquals("Cadastrar Diretor")){

                        Diretor diretor = new Diretor(nomeText , dataNascimentoText, R.drawable.pessoa);
                        controllerDiretor.addDiretor(diretor);
                        aux = "Diretor";
                        fragmentTransaction.replace(R.id.container_fragment, new RecyclerFragmentDiretor(activity));
                    }

                    else if(typeRegister.contentEquals("Cadastrar Ator")){

                        Ator ator = new Ator(nomeText,dataNascimentoText, R.drawable.pessoa);
                        controllerAtor.addAtor(ator);
                        fragmentTransaction.replace(R.id.container_fragment, new RecyclerFragmentAtor(activity));
                        aux = "Ator";
                    }
                    Toast.makeText(getContext(), aux+" cadastrado com sucesso !", Toast.LENGTH_SHORT).show();

                    Thread.sleep(100);


                    fragmentTransaction.commit();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
