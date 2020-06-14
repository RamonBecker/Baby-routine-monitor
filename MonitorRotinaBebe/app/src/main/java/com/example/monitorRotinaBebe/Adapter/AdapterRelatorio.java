package com.example.monitorRotinaBebe.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.RelatorioDormiu;
import com.example.monitorRotinaBebe.entites.RelatorioMamou;
import com.example.monitorRotinaBebe.entites.RelatorioRotina;
import com.example.monitorRotinaBebe.entites.RelatorioTrocou;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AdapterRelatorio extends RecyclerView.Adapter<AdapterRelatorio.MyViewHolder> {
    private DaoEventoBebe daoEventoBebe;
    private AppCompatActivity activity;
    private String dataAtual;
    private SimpleDateFormat dataFormatada;
    private Date hora_data_atual;


    public AdapterRelatorio(AppCompatActivity activity) {
        this.activity = activity;
        this.daoEventoBebe = new DaoEventoBebe(this.activity);
        daoEventoBebe.inicializarRelatorios();
        daoEventoBebe.n_vezes_Trocados_Mamou(getDataAtual());
        carregarDados();

    }

    private String getDataAtual() {
        dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        hora_data_atual = Calendar.getInstance().getTime();
        dataAtual = dataFormatada.format(hora_data_atual);
        return dataAtual;
    }


    private void carregarDados() {
        for (String data : DaoEventoBebe.getDatas()) {
           // daoEventoBebe.horasDormidas(data);
            daoEventoBebe.calcDorme(data);
            daoEventoBebe.n_vezes_Trocados_Mamou(data);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_resumo_ultimos_dias, parent, false);
        return new AdapterRelatorio.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RelatorioRotina relatorioRotina = daoEventoBebe.getRelatorioRotinas().get(position);
        RelatorioDormiu relatorioDormiu = relatorioRotina.getRelatorioDormiu();
        RelatorioMamou relatorioMamou = relatorioRotina.getRelatorioMamou();
        RelatorioTrocou relatorioTrocou = relatorioRotina.getRelatorioTrocou();

        Log.i("Lista  de dormiu", "" + relatorioRotina.getListDormiu());
        Log.i("Lista  de acordou", "" + relatorioRotina.getListAcordou());

        if (relatorioDormiu != null) {
            String horas = String.valueOf(relatorioDormiu.getHoras());
            String minutos = String.valueOf(relatorioDormiu.getMinutos());
            String eventoDormiu = String.valueOf(relatorioDormiu.getEvento());
            holder.relatorioDormiu.setText(eventoDormiu + " " + horas + " horas " + minutos + " minutos");
        } else {
            holder.relatorioDormiu.setText("O bebê ainda não dormiu");
        }

        if (relatorioMamou != null) {
            String n_vezes_mamou = String.valueOf(relatorioMamou.getN_vezes_bebe_mamou());
            String eventoMamou = String.valueOf(relatorioRotina.getRelatorioMamou().getEvento());
            holder.relatorioMamou.setText(eventoMamou + " " + n_vezes_mamou + " vezes");
        } else {
            holder.relatorioMamou.setText("O bebê ainda não mamou");
        }

        if (relatorioTrocou != null) {

            String n_vezes_trocado = String.valueOf(relatorioTrocou.getN_vezes_bebe_trocado());
            String eventoTrocou = relatorioTrocou.getEvento();

            holder.relatorioTrocou.setText(eventoTrocou + " " + n_vezes_trocado + " vezes");
        } else {
            holder.relatorioTrocou.setText("O bebê ainda não foi trocado");
        }

        holder.data.setText(relatorioRotina.getData());
    }

    @Override
    public int getItemCount() {
        return daoEventoBebe.getRelatorioRotinas().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView data;
        private TextView relatorioMamou;
        private TextView relatorioTrocou;
        private TextView relatorioDormiu;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textViewDataRelatorio);
            relatorioMamou = itemView.findViewById(R.id.textViewRelatorioMamou);
            relatorioTrocou = itemView.findViewById(R.id.textViewRelatorioTrocado);
            relatorioDormiu = itemView.findViewById(R.id.textViewRelatorioDormiu);
            imageView = itemView.findViewById(R.id.imageViewBebeRelatorio);
        }
    }

}
