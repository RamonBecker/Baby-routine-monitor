package com.example.monitorRotinaBebe.controller;

import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Ator;
import java.util.ArrayList;
import java.util.List;

public class ControllerAtor{
    private static ControllerAtor controllerAtor;
    private List<Ator> listAtor;

    private ControllerAtor(){
        listAtor = new ArrayList<>();
        criarAtor();
    }

    public static  ControllerAtor getInstance(){
        if(controllerAtor == null){
            controllerAtor = new ControllerAtor();
        }
        return controllerAtor;
    }

    public void addAtor(Ator ator){
        this.listAtor.add(ator);
    }

    private void criarAtor(){
        listAtor.add(new Ator("Joaquin Phoenix","28/10/1974", R.drawable.joaquin_phoenix));
        listAtor.add(new Ator("Mel Gibson","03/01/1956",R.drawable.mel_gibson));
        listAtor.add(new Ator("Tom Hanks", "09/07/1956", R.drawable.tom_hanks));
        listAtor.add(new Ator("Damiam Lewis","11/02/1971", R.drawable.damian_lewis));
    }

    public Ator buscarAtor(String nome){
        for (Ator ator: listAtor) {
            if(ator.getNome().equals(nome)){
                return ator;
            }
        }
        return null;
    }

    public List<Ator> getListAtor() {
        return listAtor;
    }
}
