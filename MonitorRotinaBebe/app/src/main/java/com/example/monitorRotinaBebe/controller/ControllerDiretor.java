package com.example.monitorRotinaBebe.controller;

import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Diretor;

import java.util.ArrayList;
import java.util.List;

public class ControllerDiretor {

    private static ControllerDiretor controllerDiretor;
    private List<Diretor> listDiretor;

    private ControllerDiretor(){
        this.listDiretor = new ArrayList<>();
        criarDiretor();
    }

    public static ControllerDiretor getInstance(){
        if(controllerDiretor == null){
            controllerDiretor = new ControllerDiretor();
        }
        return  controllerDiretor;
    }

    public void criarDiretor(){
        this.listDiretor.add(new Diretor("Todd Phillps", "20/12/1970", R.drawable.todd_phillips));
        this.listDiretor.add(new Diretor("Mel Gibson","03/01/1956", R.drawable.mel_gibson));
        this.listDiretor.add(new Diretor("Steven Spielberg ","18/12/1946",R.drawable.steven_spielberg));
        this.listDiretor.add(new Diretor("Stephen Ambrose","10/01/1936/",R.drawable.stephen_ambrose));

    }

    public void addDiretor(Diretor diretor){
        this.listDiretor.add(diretor);
    }


    public Diretor buscarDiretor(String nome){
        for (Diretor diretor : listDiretor) {
            if(diretor.getNome().equals(nome)){
                return  diretor;
            }
        }
        return null;
    }

    public List<Diretor> getListDiretor() {
        return listDiretor;
    }

    public void setListDiretor(List<Diretor> listDiretor) {
        this.listDiretor = listDiretor;
    }
}
