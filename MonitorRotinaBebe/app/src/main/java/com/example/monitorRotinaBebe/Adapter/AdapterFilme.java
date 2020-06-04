package com.example.monitorRotinaBebe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.activity.MainActivity;
import com.example.monitorRotinaBebe.controller.ControllerFilme;
import com.example.monitorRotinaBebe.entites.Filme;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class AdapterFilme extends RecyclerView.Adapter<AdapterFilme.MyViewHolder> {

    private ControllerFilme controllerFilme;
    private MainActivity activity;


    public AdapterFilme(){

    }

    public AdapterFilme(MainActivity activity) {
        this.activity = activity;
        this.controllerFilme = ControllerFilme.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_filme, parent, false);
        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        Filme filme = controllerFilme.getListaFilme().get(position);
        holder.titulo.setText(filme.getTitulo());
        holder.genero.setText(filme.getGenero());
        holder.anoLancamento.setText(filme.getAnoLançamento());
        holder.imagem.setImageResource(filme.getIdImagem());
        holder.ator.setText(filme.getAtor().getNome());
        holder.diretor.setText(filme.getDiretor().getNome());

    }

    public void mover(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(controllerFilme.getListaFilme(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(controllerFilme.getListaFilme(), i, i - 1);
            }

            notifyItemMoved(fromPosition, toPosition);
        }

    }

    public void remover(int position) {
        final int posicaoRemovida = position;
        final Filme filmeRemovido = controllerFilme.getListaFilme().get(position);
        controllerFilme.getListaFilme().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.RelativeLayout), "Item deletado", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer ?", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controllerFilme.getListaFilme().add(posicaoRemovida, filmeRemovido);
                notifyItemInserted(posicaoRemovida);
            }
        });
        snackbar.show();
    }


    @Override
    public int getItemCount() {
        return controllerFilme.getListaFilme().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView genero;
        private TextView anoLancamento;
        private ImageView imagem;
        private TextView diretor;
        private TextView ator;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloFilme);
            genero = itemView.findViewById(R.id.textViewGenero);
            anoLancamento = itemView.findViewById(R.id.textViewAnoLancamento);
            imagem = itemView.findViewById(R.id.imageViewFilm);
            diretor = itemView.findViewById(R.id.textViewDiretor);
            ator = itemView.findViewById(R.id.textViewProtagonista);
        }
    }
}


