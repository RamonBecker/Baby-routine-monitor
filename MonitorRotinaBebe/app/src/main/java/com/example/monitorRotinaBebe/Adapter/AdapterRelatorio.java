package com.example.monitorRotinaBebe.Adapter;

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
import com.example.monitorRotinaBebe.entites.RelatorioRotina;
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
            daoEventoBebe.horasDormidas(data);
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

        String horas = String.valueOf(relatorioRotina.getRelatorioDormiu().getHoras());
        String minutos = String.valueOf(relatorioRotina.getRelatorioDormiu().getMinutos());
        String eventoDormiu = String.valueOf(relatorioRotina.getRelatorioDormiu().getEvento());

        String n_vezes_trocado = String.valueOf(relatorioRotina.getRelatorioTrocou().getN_vezes_bebe_trocado());
        String eventoTrocou = relatorioRotina.getRelatorioTrocou().getEvento();

        String n_vezes_mamou = String.valueOf(relatorioRotina.getRelatorioMamou().getN_vezes_bebe_trocado());
        String eventoMamou = String.valueOf(relatorioRotina.getRelatorioMamou().getEvento());

        holder.relatorioDormiu.setText(eventoDormiu + " " + horas + " " + minutos);
        holder.relatorioTrocou.setText(eventoTrocou + " " + n_vezes_trocado);
        holder.relatorioMamou.setText(eventoMamou + " " + n_vezes_mamou);
    }

    @Override
    public int getItemCount() {
        return daoEventoBebe.getRelatorioRotinas().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView relatorioMamou;
        private TextView relatorioTrocou;
        private TextView relatorioDormiu;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            relatorioMamou = itemView.findViewById(R.id.textViewRelatorioMamou);
            relatorioTrocou = itemView.findViewById(R.id.textViewRelatorioTrocado);
            relatorioDormiu = itemView.findViewById(R.id.textViewRelatorioDormiu);
            imageView = itemView.findViewById(R.id.imageViewBebeRelatorio);
        }
    }

}
