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
import com.example.monitorRotinaBebe.controller.ControllerDiretor;
import com.example.monitorRotinaBebe.entites.Diretor;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class AdapterDiretor extends RecyclerView.Adapter<AdapterDiretor.MyViewHolder>{

    private ControllerDiretor controllerDiretor;
    private MainActivity activity;

    public AdapterDiretor(MainActivity activity){
        this.activity = activity;
        controllerDiretor = ControllerDiretor.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_diretor, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Diretor diretor = controllerDiretor.getListDiretor().get(position);
        holder.nome.setText(diretor.getNome());
        holder.dataNascimento.setText(diretor.getDataNascimento());
        holder.imageView.setImageResource(diretor.getIdImagem());
    }


    public void mover(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(controllerDiretor.getListDiretor(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(controllerDiretor.getListDiretor(), i, i - 1);
            }

            notifyItemMoved(fromPosition, toPosition);
        }

    }
    public void remover(int position) {
        final int posicaoRemovida = position;
        final Diretor diretorRemovido = controllerDiretor.getListDiretor().get(position);
        controllerDiretor.getListDiretor().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.RelativeLayout), "Item deletado", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer ?", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controllerDiretor.getListDiretor().add(posicaoRemovida, diretorRemovido);
                notifyItemInserted(posicaoRemovida);
            }
        });
        snackbar.show();
    }




    @Override
    public int getItemCount() {
        return controllerDiretor.getListDiretor().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;
        private TextView dataNascimento;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewNomeDiretor);
            dataNascimento = itemView.findViewById(R.id.textViewAnoNascimentoDiretor);
            imageView = itemView.findViewById(R.id.imageViewDiretor);
        }
    }
}
