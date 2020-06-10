package com.example.monitorRotinaBebe.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorRotinaBebe.R;

public class AdapterRelatorio extends  RecyclerView.Adapter<AdapterRelatorio.MyViewHolder>  {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

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
