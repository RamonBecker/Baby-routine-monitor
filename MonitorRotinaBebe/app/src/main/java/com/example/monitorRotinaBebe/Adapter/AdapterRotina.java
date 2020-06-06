package com.example.monitorRotinaBebe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.BD.AppDataBase;
import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.fragments.FragmentoEditarRotinaBebe;
import com.example.monitorRotinaBebe.threads.DeletarRotina;
import com.example.monitorRotinaBebe.threads.RetornarRotinaDia;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdapterRotina extends RecyclerView.Adapter<AdapterRotina.MyViewHolder> {

    private AppCompatActivity activity;
    private AppDataBase db;
    private RetornarRotinaDia retornarRotinaDia;
    private SimpleDateFormat dataFormatada;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int posicaoRemovidoRecentemente;
    private Rotina rotinaRemovidaRecentemente;
    private DeletarRotina deletarRotina;
    private DaoEventoBebe daoEventoBebe;


    public AdapterRotina(AppCompatActivity activity) {
        this.activity = activity;

        carregarDadosRotinaDia();
    }

    private void carregarDadosRotinaDia() {

        retornarRotinaDia = new RetornarRotinaDia(activity);
        dataFormatada = new SimpleDateFormat("y:M:d");
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        retornarRotinaDia.setData(dataAtual);
        AppDataBase.databaseWriteExecutor.execute(retornarRotinaDia);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_rotina_bebe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Rotina rotina = retornarRotinaDia.getRotinas().get(position);
        holder.hora.setText(rotina.getHora());
        holder.data.setText(rotina.getData());
        holder.evento.setText(rotina.getEvento());
        holder.imageView.setImageResource(rotina.getIdImagem());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("Rotina",""+rotina);
                fragmentManager = activity.getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new FragmentoEditarRotinaBebe(rotina, activity));
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return retornarRotinaDia.getRotinas().size();
    }


    public void remover(int posicao) {
        posicaoRemovidoRecentemente = posicao;
        rotinaRemovidaRecentemente = retornarRotinaDia.getRotinas().get(posicao);

        Rotina rotina_a_ser_removida = retornarRotinaDia.getRotinas().get(posicao);
        deletarRotina = new DeletarRotina(rotina_a_ser_removida, activity);
        AppDataBase.databaseWriteExecutor.execute(deletarRotina);
        retornarRotinaDia.getRotinas().remove(posicao);
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, this.getItemCount());

        daoEventoBebe = new DaoEventoBebe(activity);


        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.RelativeLayout), "Item deletado",Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoEventoBebe.inserirRotina(rotinaRemovidaRecentemente);
                retornarRotinaDia.getRotinas().add(posicaoRemovidoRecentemente,rotinaRemovidaRecentemente);
                notifyItemInserted(posicaoRemovidoRecentemente);

            }
        });
        snackbar.show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hora;
        private TextView data;
        private TextView evento;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hora = itemView.findViewById(R.id.textViewHorarioRotina);
            data = itemView.findViewById(R.id.textViewDataRotina);
            evento = itemView.findViewById(R.id.textViewEventoRotina);
            imageView = itemView.findViewById(R.id.imageViewRotinaBebe);
        }
    }
}
