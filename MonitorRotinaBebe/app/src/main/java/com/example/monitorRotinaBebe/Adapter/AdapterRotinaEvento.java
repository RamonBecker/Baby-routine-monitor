package com.example.monitorRotinaBebe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AdapterRotinaEvento extends RecyclerView.Adapter<AdapterRotinaEvento.MyViewHolder> {

    private AppCompatActivity activity;
    private AppDataBase db;
    private DaoEventoBebe daoEventoBebe;

    public AdapterRotinaEvento(AppCompatActivity activity, String evento){
        this.activity = activity;
        this.daoEventoBebe = new DaoEventoBebe(activity);
        carregarDadosRotinaEvento(evento);

    }

    private void carregarDadosRotinaEvento(String evento){

        SimpleDateFormat dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        daoEventoBebe.setEvento(evento);
        daoEventoBebe.setData(dataAtual);
        daoEventoBebe.getRotinaEvento();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_rotina_bebe, parent, false);
        return new AdapterRotinaEvento.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Rotina rotina = DaoEventoBebe.getRotinasEvento().get(position);
        holder.hora.setText(rotina.getHora());
        holder.data.setText(rotina.getData());
        holder.evento.setText(rotina.getEvento());
        holder.imageView.setImageResource(rotina.getIdImagem());
    }

    @Override
    public int getItemCount() {
        return  DaoEventoBebe.getRotinasEvento().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView hora;
        private TextView data;
        private TextView evento;
        private ImageView imageView;
        private Button botaoExcluir;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hora = itemView.findViewById(R.id.textViewHorarioRotina);
            data = itemView.findViewById(R.id.textViewDataRotina);
            evento = itemView.findViewById(R.id.textViewEventoRotina);
            imageView = itemView.findViewById(R.id.imageViewRotinaBebe);
            botaoExcluir = itemView.findViewById(R.id.buttonExcluirRotina);
        }
    }
}
