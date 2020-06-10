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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.BD.DaoEventoBebe;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Rotina;
import com.example.monitorRotinaBebe.fragments.FragmentoEditarRotinaBebe;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AdapterRotina extends RecyclerView.Adapter<AdapterRotina.MyViewHolder> {

    private AppCompatActivity activity;
    //   private AppDataBase db;
    //  private RetornarRotinaDia retornarRotinaDia;
    private SimpleDateFormat dataFormatada;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int posicaoRemovidoRecentemente;
    private Rotina rotinaRemovidaRecentemente;
    private DaoEventoBebe daoEventoBebe;
    //  private DeletarTodasRotinas deletarTodasRotinas;

    public AdapterRotina(AppCompatActivity activity) {
        this.activity = activity;
        this.daoEventoBebe = new DaoEventoBebe(activity);
        carregarDadosRotinaDia();
    }

    private void carregarDadosRotinaDia() {

        //  retornarRotinaDia = new RetornarRotinaDia(activity);
        dataFormatada = new SimpleDateFormat("y:M:d");
        dataFormatada.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date hora_data_atual = Calendar.getInstance().getTime();
        String dataAtual = dataFormatada.format(hora_data_atual);
        daoEventoBebe.setData(dataAtual);
        daoEventoBebe.getRotinaDoDia();
        // retornarRotinaDia.setData(dataAtual);
        //    AppDataBase.databaseWriteExecutor.execute(retornarRotinaDia);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_rotina_bebe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Rotina rotina = DaoEventoBebe.getRotinasdoDia().get(position);
        // retornarRotinaDia.getRotinas().get(position);
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


        holder.botaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return DaoEventoBebe.getRotinasdoDia().size();

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
        rotinaRemovidaRecentemente = DaoEventoBebe.getRotinasdoDia().get(posicao);
        //retornarRotinaDia.getRotinas().get(posicao);

        Rotina rotina_a_ser_removida = DaoEventoBebe.getRotinasdoDia().get(posicao);
        //retornarRotinaDia.getRotinas().get(posicao);
        DaoEventoBebe.getRotinasdoDia().remove(posicao);
        daoEventoBebe.setRotina(rotinaRemovidaRecentemente);
        daoEventoBebe.removerRotina();
        // deletarRotina = new DeletarRotina(rotina_a_ser_removida, activity);
        //AppDataBase.databaseWriteExecutor.execute(deletarRotina);

        // retornarRotinaDia.getRotinas().remove(posicao);
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, this.getItemCount());

        //daoEventoBebe = new DaoEventoBebe(activity);


        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.RelativeLayout), "Item deletado", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoEventoBebe.inserirRotina(rotinaRemovidaRecentemente);
                DaoEventoBebe.getRotinasdoDia().add(posicaoRemovidoRecentemente, rotinaRemovidaRecentemente);
                //     retornarRotinaDia.getRotinas().add(posicaoRemovidoRecentemente,rotinaRemovidaRecentemente);
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
