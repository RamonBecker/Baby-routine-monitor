package com.example.monitorRotinaBebe.fragments;

import android.app.TimePickerDialog;
import android.media.session.MediaController;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monitorRotinaBebe.R;

import java.util.Calendar;

public class FragmentoEditarRotinaBebe extends Fragment {
    private Button button;
    private  TextView selecionarhorario;
    private EditText horario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.editar_rotina_bebe, container, false);

        button = view.findViewById(R.id.botaoEditarRotina);
        selecionarhorario = view.findViewById(R.id.clicarEditarHorario);
        horario = view.findViewById(R.id.editarHoraEvento);

        Calendar calendar = Calendar.getInstance();
        final int hora = calendar.get(Calendar.HOUR_OF_DAY);
        final int minuto = calendar.get(Calendar.MINUTE);

        acaoSelecionarHorario(selecionarhorario, horario, hora, minuto);
        return view;
    }

    private void acaoSelecionarHorario(final TextView textView, final EditText  editText, final int hora, final int minuto){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hora = String.valueOf(hourOfDay);
                        String minuto = String.valueOf(minute);
                        editText.setText(hora+":"+minuto);
                    }
                },hora, minuto, android.text.format.DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();
            }
        });
    }
}
