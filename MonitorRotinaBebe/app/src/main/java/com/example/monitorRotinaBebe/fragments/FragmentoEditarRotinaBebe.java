package com.example.monitorRotinaBebe.fragments;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.Calendar;

public class FragmentoEditarRotinaBebe extends Fragment {

    private Button button;
    private  TextView selecionarhorario;
    private EditText horario_Atual_Rotina;
    private EditText evento;
    private EditText horario;
    private Rotina rotina;
    private AppCompatActivity activity;
    private DaoEventoBebe daoEventoBebe;

    public FragmentoEditarRotinaBebe(){
    }

    public  FragmentoEditarRotinaBebe(Rotina rotina, AppCompatActivity activity){
        this.rotina = rotina;
        this.activity = activity;
        daoEventoBebe = new DaoEventoBebe(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.editar_rotina_bebe, container, false);

        button = view.findViewById(R.id.botaoEditarRotina);
        selecionarhorario = view.findViewById(R.id.clicarEditarHorario);
        horario = view.findViewById(R.id.editarHoraEvento);
        horario_Atual_Rotina = view.findViewById(R.id.editarHorario);
        evento = view.findViewById(R.id.editarEvento);

        horario_Atual_Rotina.setText(rotina.getHora());
        evento.setText(rotina.getEvento());

        Calendar calendar = Calendar.getInstance();
        final int hora = calendar.get(Calendar.HOUR_OF_DAY);
        final int minuto = calendar.get(Calendar.MINUTE);

        acaoSelecionarHorario(selecionarhorario, horario, hora, minuto);
        botaoconfirmarAcao(button);
        //retornarRotinaDia = new RetornarRotinaDia(activity);
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

    private void botaoconfirmarAcao(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Atenção");
        alert.setMessage("Esta ação poderá gerar inconsistência nos dados futuramente !\n" +
                "Deseja realizar esta operação ?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Rotina atualizada", Toast.LENGTH_SHORT).show();
                rotina.setHora(String.valueOf(horario.getText()));

            //    atualizarRotina = new AtualizarRotina(rotina, (AppCompatActivity) getActivity());
              //  AppDataBase.databaseWriteExecutor.execute(atualizarRotina);
               // AppDataBase.databaseWriteExecutor.execute(retornarRotinaDia);
                daoEventoBebe.setRotina(rotina);
                daoEventoBebe.atualizarRotina();
                initializeFragment();
            }
        });

        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                horario.setText("");
                Toast.makeText(getContext(), "Atualização de rotina cancelada", Toast.LENGTH_SHORT).show();
                initializeFragment();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
    private void initializeFragment(){
          FragmentManager fragmentManager = activity.getSupportFragmentManager();
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.container_fragment, new FragmentoRecyclerRotinaDoDia());
          fragmentTransaction.commit();
    }

}
