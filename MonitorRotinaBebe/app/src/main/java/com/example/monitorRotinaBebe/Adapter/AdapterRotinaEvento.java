package com.example.monitorRotinaBebe.Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class AdapterRotinaEvento extends RecyclerView.Adapter<AdapterRotinaEvento.MyViewHolder> {

    private AppCompatActivity activity;
    private AppDataBase db;
    private DaoEventoBebe daoEventoBebe;
    private int posicaoRemovidoRecentemente;
    private Rotina rotinaRemovidaRecentemente;

    public AdapterRotinaEvento(AppCompatActivity activity, String evento) {
        this.activity = activity;
        this.daoEventoBebe = new DaoEventoBebe(activity);
        carregarDadosRotinaEvento(evento);

    }

    private void carregarDadosRotinaEvento(String evento) {

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Rotina rotina = DaoEventoBebe.getRotinasEvento().get(position);
        holder.hora.setText(rotina.getHora());
        holder.data.setText(rotina.getData());
        holder.evento.setText(rotina.getEvento());
        holder.imageView.setImageResource(rotina.getIdImagem());

        holder.botaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return DaoEventoBebe.getRotinasEvento().size();
    }


    private void openDialog(final int posicao) {

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Atenção");
        alert.setMessage("Esta ação poderá gerar inconsistência nos dados futuramente !\n" +
                "Deseja realizar esta operação ?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  Toast.makeText(activity, "Rotina excluída", Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, "Rotina excluída", Toast.LENGTH_SHORT).show();
                remover(posicao);
            }
        });

        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "Ação cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    public void remover(int posicao) {
        posicaoRemovidoRecentemente = posicao;
        rotinaRemovidaRecentemente = DaoEventoBebe.getRotinasEvento().get(posicao);

        Rotina rotina_a_ser_removida = DaoEventoBebe.getRotinasEvento().get(posicao);
        DaoEventoBebe.getRotinasEvento().remove(posicao);

        daoEventoBebe.setRotina(rotinaRemovidaRecentemente);
        daoEventoBebe.removerRotina();

        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.RelativeLayout), "Item deletado", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoEventoBebe.inserirRotina(rotinaRemovidaRecentemente);
                DaoEventoBebe.getRotinasEvento().add(posicaoRemovidoRecentemente, rotinaRemovidaRecentemente);
                notifyItemInserted(posicaoRemovidoRecentemente);

            }
        });
        snackbar.show();
    }

    public void mover(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = 0; i < toPosition; i++) {
                Collections.swap(DaoEventoBebe.getRotinasEvento(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(DaoEventoBebe.getRotinasEvento(), i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
