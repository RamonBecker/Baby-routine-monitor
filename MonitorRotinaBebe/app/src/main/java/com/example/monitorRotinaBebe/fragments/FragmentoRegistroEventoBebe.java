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
import androidx.fragment.app.Fragment;

import com.example.monitorRotinaBebe.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentoRegistroEventoBebe extends Fragment {

    private Spinner spinner_eventos_bebe;
    private Button buttonRegistrarEvento;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registro_evento_bebe, container, false);

        spinner_eventos_bebe = view.findViewById(R.id.spinner_eventos_bebe);
        buttonRegistrarEvento = view.findViewById(R.id.buttonRegistrarEventoBebe);
        popularSpinner(spinner_eventos_bebe,getContext());
        açãoBotaoRegistrarEvento(buttonRegistrarEvento);

        return view;
    }

    private void popularSpinner(Spinner spinner, Context context){
        List<String> eventos_bebe = new ArrayList<>();
        eventos_bebe.add("Acordou");
        eventos_bebe.add("Mamou");
        eventos_bebe.add("Trocou");
        eventos_bebe.add("Dormiu");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,eventos_bebe);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void açãoBotaoRegistrarEvento(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelectedItem(v);
            }
        });
    }

    public void getSelectedItem(View view){
        String evento  = (String) spinner_eventos_bebe.getSelectedItem();
        Toast.makeText(view.getContext(), ""+evento, Toast.LENGTH_SHORT).show();
    }
}
